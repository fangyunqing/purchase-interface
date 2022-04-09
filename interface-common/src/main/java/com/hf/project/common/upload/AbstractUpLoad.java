package com.hf.project.common.upload;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.hf.project.common.entity.DataWrapper;
import com.hf.project.common.entity.MailRecipient;
import com.hf.project.common.entity.User;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.entity.enums.ActionName;
import com.hf.project.common.entity.properties.InterfaceUrl;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.exception.FieldRequireException;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.SendMail;
import com.hf.project.common.exception.billcodeexception.NoDataBillCodeException;
import com.hf.project.common.filter.upload.UploadFilter;
import com.hf.project.common.helper.MailHelper;
import com.hf.project.common.helper.ValidHelper;
import com.hf.project.common.service.UserService;
import com.hf.project.common.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 上传抽象类
 * @Author fyq
 * @Date 2021/5/12 17:08
 **/

public abstract class AbstractUpLoad<T> implements UpLoad {

    @Autowired
    @Qualifier(value = "restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private ValidHelper validHelper;

    @Autowired
    private MailHelper mailHelper;

    @Autowired
    protected RequiredProperties requiredProperties;

    @Autowired
    protected InterfaceUrl interfaceUrl;

    @Autowired
    private UserService userService;

    @Autowired
    private List<UploadFilter> uploadFilterList;

    private static final Logger log = LogManager.getRootLogger();

    @Override
    public String upLoad(String codes, String workCode) {

        try{
            // 解析codes
            Assert.notNull(codes, "codes must not be null");
            List<String> codeList = Arrays.stream(codes.split(",")).distinct().collect(Collectors.toList());
            // 获取数据 如果codes中有一个code查不到数据 抛出异常
            List list = getData(codeList);
            // 获取Object
            Object object = getObject(list);
            // 过滤判断
            for (UploadFilter uploadFilter : uploadFilterList) {
                if (!uploadFilter.filter(object)) {
                    return uploadFilter.getErrorMessage();
                }
            }
            // 加前缀 HSRZ 和 ZZ 不加
            if (!requiredProperties.getSysCode().equals("HSRZ") && !requiredProperties.getSysCode().equals("ZZ")) {
                CommonUtil.prefix(object, CommonUtil.getPrefix(requiredProperties.getSysCode()), 0, "code", "materialCode");
            }
            // 格式化json
            String json = JSONObject.toJSONString(object,true);
            try{
                // 验证数据
                validHelper.valid(object);
                // 打印日志
                log.info("请求数据:" + json);
                // json数据
                handleJson(json);
                // testMode 测试模式
                if (requiredProperties.getTestMode()) {
                    return "1";
                }
                else {
                    // 封装http请求
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.valueOf("application/json;charset=UTF-8"));
                    HttpEntity<String> commonInsertHttpEntity = new HttpEntity<>(json,headers);
                    // 发送http请求
                    ResponseEntity<CommonOutput> commonOutputResponseEntity = restTemplate.postForEntity(getUpLoadUrl(), commonInsertHttpEntity, CommonOutput.class);

                    log.info("返回数据:" + JSONObject.toJSONString(commonOutputResponseEntity.getBody()));
                    // 后续处理
                    return afterUpLoad(commonOutputResponseEntity.getBody(), codeList, workCode);
                }


            } catch (FieldRequireException e) {
                mailHelper.sendLocalExceptionMail(json, e.getMessage());
                return e.getMessage();

            }

        } catch (InterFaceException ie) {

            log.error(ie);

            // SendMail 发送邮件
            if (ie instanceof SendMail) {
                mailHelper.sendInvokeExceptionMail(ie.getMessage());
            }

            return ie.getMessage();
        } catch (Exception e) {

            log.error(e);

            String errMessage = ObjectUtil.isNotNull(e.getCause()) ? e.getCause().getMessage() : e.getMessage();
            return StrUtil.format("{}-ERROR[{}]",
                    StrUtil.blankToDefault(requiredProperties.getSysName(),"未知系统"),
                    StrUtil.blankToDefault(errMessage, e.getClass().getName()));
        }
    }



    /**
     * @Author fyq
     * @Description 查询数据库
     * @Date 9:19 2021/8/30
     * @Param [codes]
     * @return com.hf.project.common.entity.UpLoadAssist
     **/
    private List getData(List<String> codes) throws NoDataBillCodeException {

        DataWrapper dataWrapper = doGetData(codes);

        List<String> strings = HandlerCodes(codes);
        strings.removeAll(dataWrapper.getBillCodes());

        NoDataBillCodeException noDataBillCodeException = new NoDataBillCodeException(requiredProperties.getSysName(), getUploadName(), getActionName());
        noDataBillCodeException.addAll(strings);

        if (noDataBillCodeException.getCodeSize() > 0) {
            throw noDataBillCodeException;
        }

        return dataWrapper.getDataList();
    }

    /**
     * @Author fyq
     * @Description 查询数据库
     * @Date 9:19 2021/8/30
     * @Param [codes]
     * @return com.hf.project.common.entity.UpLoadAssist
     **/
     protected DataWrapper doGetData(List<String> codes) {

        DataWrapper dataWrapper = new DataWrapper();

        List<T> list = queryDataBase(codes);
        dataWrapper.setDataList(filterData(list));
        dataWrapper.setBillCodes(getBillCodes(list));

        return dataWrapper;
    }

    /**
     * @Author fyq
     * @Description 查询数据库
     * @Date 11:40 2021/9/13
     * @Param []
     * @return java.util.List<T>
     **/
    protected abstract List<T> queryDataBase(List<String> codes);


    /**
     * @Author fyq
     * @Description 过滤数据
     * @Date 11:43 2021/9/13
     * @Param [list]
     * @return java.util.List
     **/
    protected List filterData(List<T> list) {
        return list;
    }

    /**
     * @Author fyq
     * @Description 获取单据号
     * @Date 11:46 2021/9/13
     * @Param [list]
     * @return java.util.List<java.lang.String>
     **/
    protected abstract List<String> getBillCodes(List<T> list);

    /**
     * @Author fyq
     * @Description 接口地址
     * @Date 16:45 2021/8/24
     * @Param []
     * @return java.lang.String
     **/
    protected abstract String getUpLoadUrl();

    /**
     * @Author fyq
     * @Description 上传完后结果处理
     * @Date 16:51 2021/8/24
     * @Param [commonOutput, upLoadAssist]
     * @return void
     **/
    private String afterUpLoad(@NotNull CommonOutput commonOutput,
                               List<String> codes,
                               String workCode) {

        String title = requiredProperties.getSysName() + "[" + getUploadName() + "]上传采购云(" + getActionName().getDescription() + ")";
        // 获取邮箱地址信息
        String emailAddress = null;
        if (ObjectUtil.isNotNull(workCode)) {
            User user = userService.getUser(workCode);
            if (ObjectUtil.isNotNull(user)) {
                emailAddress = user.getEmailAddress();
            }
        }

        //  用友 0 => 成功 1 => 失败
        if (commonOutput.getResult() == 0) {

            final String subject = title + "成功";
            uploadSuccess(commonOutput, codes);

            if (ObjectUtil.isNotNull(emailAddress) && requiredProperties.getSuccessMail()) {

                final String[] emailAddressArray = emailAddress.split("");
                mailHelper.sendSuccessUploadMessage(subject, codes,
                        () -> {

                            MailRecipient mailRecipient = new MailRecipient();
                            mailRecipient.setMailTo(emailAddressArray);
                            mailRecipient.setSubject(subject);
                            return mailRecipient;

                        });

            }

            return "1";

        }
        else {
            final String subject = title + "失败";
            uploadFail(commonOutput, codes);

            if (ObjectUtil.isNotNull(emailAddress) && ( requiredProperties.getFailMail() || requiredProperties.getFailMaintenanceStaffMail())) {

                final String[] emailAddressArray = emailAddress.split("");
                mailHelper.sendFailUploadMessage(subject, codes, commonOutput.getErrorMsg(),
                        () -> {

                            MailRecipient mailRecipient = new MailRecipient();
                            if (requiredProperties.getFailMail() && requiredProperties.getFailMaintenanceStaffMail()){
                                mailRecipient.setMailTo(emailAddressArray);
                                mailRecipient.setMailCc(requiredProperties.getMaintenanceStaffMail());
                            }
                            else {
                                String[] to = requiredProperties.getFailMail() ? emailAddressArray : requiredProperties.getMaintenanceStaffMail();
                                mailRecipient.setMailTo(to);
                            }

                            mailRecipient.setSubject(subject);
                            return mailRecipient;

                        });

            }

            return StrUtil.blankToDefault(commonOutput.getErrorMsg(), "未知错误");
        }
    }

    /**
     * @Author fyq
     * @Description 获取 CommonInsert 对象
     * @Date 16:47 2021/8/24
     * @Param [dataList]
     * @return java.lang.Object
     **/
    protected abstract Object getObject(List dataList);
    /**
     * @Author fyq
     * @Description 上传数据时的json处理 默认不实现
     * @Date 16:46 2021/8/24
     * @Param [json]
     * @return void
     **/
    protected void handleJson(String json) {

    }

    /**
     * @Author fyq
     * @Description 上传失败调用
     * @Date 16:55 2021/8/24
     * @Param [commonOutput, upLoadAssist]
     * @return void
     **/
    protected void uploadFail(CommonOutput commonOutput, List<String> codes) {

    }

    /**
     * @Author fyq
     * @Description 上传成功调用
     * @Date 16:55 2021/8/24
     * @Param [commonOutput, upLoadAssist]
     * @return void
     **/
    protected void uploadSuccess(CommonOutput commonOutput, List<String> codes) {

    }

    /**
     * @Author fyq
     * @Description 上传名字 入库单 采购单
     * @Date 17:02 2021/8/24
     * @Param []
     * @return java.lang.String
     **/
    protected abstract String getUploadName();

    /**
     * @Author fyq
     * @Description
     * @Date 11:53 2021/8/30
     * @Param []
     * @return java.lang.String
     **/
    protected abstract ActionName getActionName();

    /**
     * @Author fyq
     * @Description 调整codes
     * @Date 14:13 2021/12/1
     * @Param [codes]
     * @return java.util.List<java.lang.String>
     **/
    protected List<String> HandlerCodes(List<String> codes) {
        return new ArrayList<>(codes);
    }

}

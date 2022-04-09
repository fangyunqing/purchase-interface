package com.hf.project.common.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.hf.project.common.entity.base.Operator;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.entity.enums.UserRole;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.NoExistUserException;
import com.hf.project.common.exception.SendMail;
import com.hf.project.common.helper.MailHelper;
import com.hf.project.common.helper.ValidHelper;
import com.hf.project.common.mapper.common.UserMapper;
import com.hf.project.common.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;


/**
 * @Description  解析
 * @Author fyq
 * @Date 2021/5/18 20:33
 **/

public abstract class AbstractValidIHandleData<T> implements IHandlerData {

    private static final Logger log = LogManager.getRootLogger();

    @Autowired
    private ValidHelper validHelper;

    @Autowired
    private MailHelper mailHelper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Autowired
    protected UserMapper userMapper;

    @Override
    public CommonOutput handleData(String json) {

        CommonOutput commonOutput = null;

        try{
            // 解析JSON
            T t = parseJson(json);
            // 去除前缀
            if (!requiredProperties.getSysCode().equals("HSRZ") && !requiredProperties.getSysCode().equals("ZZ")) {
                CommonUtil.prefix(t, CommonUtil.getPrefix(requiredProperties.getSysCode()), 1, "code", "materialCode");
            }
            // 打印日志
            log.debug("\r\n 接收数据" + JSONObject.toJSONString(t, true));
            // 验证数据 throw FieldRequireException
            validHelper.valid(t);
            // check
            checkOperator(t);
            // 处理数据
            commonOutput = doHandleData(t);

        } catch (InterFaceException ie) {

            log.error(ie);

            commonOutput = new CommonOutput(0, ie.getMessage());

            // SendMail 发送邮件
            if (ie instanceof SendMail) {
                mailHelper.sendInvokeExceptionMail(ie.getMessage());
            }

        } catch (Exception e) {

            log.error(e);

            String errMessage = ObjectUtil.isNotNull(e.getCause()) ? e.getCause().getMessage() : e.getMessage();
            commonOutput = new CommonOutput(0,
                    StrUtil.format("{}-ERROR[{}]",
                    StrUtil.blankToDefault(requiredProperties.getSysName(),"未知系统"),
                    StrUtil.blankToDefault(errMessage, e.getClass().getName())));
        }
        // 打印回参
       log.debug("\r\n 返回参数" + JSONObject.toJSONString(commonOutput, true));

        return commonOutput;
    }

    protected abstract T parseJson(String json);

    protected abstract CommonOutput doHandleData(T t) throws InterFaceException;

    /**
     * @Author fyq
     * @Description 检索是够包含Operator对象
     * @Date 15:16 2021/9/24
     * @Param [t]
     * @return void
     **/
    private void checkOperator(Object o) throws NoExistUserException {

        if (ObjectUtil.isNotNull(o)) {

            if (o instanceof Operator) {
                doCheckOperator((Operator)o);
                return;
            }

            for (PropDesc propDesc : BeanUtil.getBeanDesc(o.getClass()).getProps()) {

                Class<?> c1 = propDesc.getFieldClass();
                Object o1 = propDesc.getValue(o);
                if (Operator.class.isAssignableFrom(c1) && ObjectUtil.isNotNull(o1)) {
                    doCheckOperator((Operator)o1);
                }
                else if (o1 instanceof Collection && ObjectUtil.isNotNull(o1)) {
                    Collection<?> collection = (Collection<?>)o1;

                    for (Object o2 : collection) {
                        checkOperator(o2);
                    }

                }
            }

        }

    }

    private void doCheckOperator(Operator operator) throws NoExistUserException {

        if (ObjectUtil.isNotNull(operator)) {

            if (operator.getCreator().equals("BIP")) {
                operator.setCreator(operator.getConfirmMan());
            }

            if (ObjectUtil.isNull(userMapper.query(operator.getCreator()))) {
                throw new NoExistUserException(requiredProperties.getSysName(), operator.getCreator(),
                        UserRole.CREATOR.getDescription());
            }

            if (ObjectUtil.isNull(userMapper.query(operator.getConfirmMan()))) {
                throw new NoExistUserException(requiredProperties.getSysName(), operator.getConfirmMan(),
                        UserRole.CONFIRM.getDescription());
            }
        }

    }

}

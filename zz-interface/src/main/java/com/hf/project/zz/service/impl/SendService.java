package com.hf.project.zz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.project.common.check.record.CheckUserService;
import com.hf.project.common.entity.BaseData;
import com.hf.project.common.entity.User;
import com.hf.project.common.entity.enums.BaseDataType;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.ExistBillCodeException;
import com.hf.project.common.exception.billcodeexception.NoDetailsBillCodeException;
import com.hf.project.common.service.PurchaseArrivalService;
import com.hf.project.common.service.datadict.DataDictService;
import com.hf.project.zz.check.billcode.CheckDeleteSendService;
import com.hf.project.zz.check.billcode.CheckExistPurService;
import com.hf.project.zz.check.record.CheckSupplierService;
import com.hf.project.zz.entity.Provider;
import com.hf.project.zz.entity.pur.PurDtl;
import com.hf.project.zz.entity.send.SendDetail;
import com.hf.project.zz.entity.send.SendHead;
import com.hf.project.zz.mapper.zz.PurMapper;
import com.hf.project.zz.mapper.zz.SendMapper;
import com.hf.project.zz.mapper.zz.UtilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 采购到货服务表
 * @Author fyq
 * @Date 2021/10/13 15:13
 **/

@Service
public class SendService implements PurchaseArrivalService<SendHead> {

    @Autowired
    private SendMapper sendMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Autowired
    private UtilMapper utilMapper;

    @Autowired
    private PurMapper purMapper;

    @Autowired
    private CheckSupplierService checkSupplierService;

    @Autowired
    private CheckUserService checkUserService;

    @Autowired
    private CheckExistPurService checkExistPurService;

    @Autowired
    private DataDictService dataDictService;

    @Autowired
    private CheckDeleteSendService checkDeleteSendService;

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "ZZTransactionManager")
    public void insertBatch(List<SendHead> list) throws InterFaceException {

        // 判断单据号是否已经存在
        final List<String> existBillCodes = new ArrayList<>();

        list.forEach(sendHead -> {
            if (sendMapper.existBillCode(sendHead.getPurchaseInNo())) {
                existBillCodes.add(sendHead.getPurchaseInNo());
            }
        });

        if (existBillCodes.size() > 0) {
            throw new ExistBillCodeException(requiredProperties.getSysName(),
                    BillCodeType.PURCHASE_ARRIVAL.getDescription(),
                    StrUtil.join(",", existBillCodes));
        }

        for (SendHead sendHead : list) {

            // 判断明细是有条数
            if (sendHead.getSendDetailList().size() == 0) {
                throw new NoDetailsBillCodeException(requiredProperties.getSysName(),
                        BillCodeType.PURCHASE_ARRIVAL.getDescription(),
                        sendHead.getPurchaseInNo());
            }

            // 供应商
            Provider provider = checkSupplierService.check(sendHead.getProviderNo());
            sendHead.setSupplierName(provider.getProviderName());
            // 原始供应商
            Provider orgProvider = checkSupplierService.check(sendHead.getOrgSupplier());
            sendHead.setOrgSupplierName(orgProvider.getProviderName());
            // 审核人
            User confirmMan = checkUserService.check(sendHead.getConfirmMan());
            sendHead.setConfirmMan(confirmMan.getUserName());
            // 获取送货单号
            sendHead.setSendNo(sendMapper.getNextVal());

            List<String> stockNoList = new ArrayList<>();
            for (SendDetail sendDetail : sendHead.getSendDetailList()) {

                // 获取采购订单
                PurDtl purDtl = checkExistPurService.check(sendDetail.getPlanNo(), sendDetail.getPlanItemId());
                // 设置单位
                BaseData materialUnitBaseData = dataDictService.
                        query(new BaseData(null, sendDetail.getUnit(), sendDetail.getUnitName(), sendDetail.getUnit(), null),
                                true, true, BaseDataType.MATERIAL_UNIT);
                sendDetail.setUnit(materialUnitBaseData.getCode());
                // 设置辅助单位
                BaseData materialUnitExBaseData = dataDictService.
                        query(new BaseData(null, sendDetail.getUnit2(), sendDetail.getUnitName2(), sendDetail.getUnit2(), null),
                                true, true, BaseDataType.MATERIAL_UNIT);
                sendDetail.setUnit2(materialUnitExBaseData.getCode());
                // 获取采购申请的仓库号
                String stockNo = utilMapper.getReqStockNo(purDtl.getRequestNo());
                Assert.isTrue(ObjectUtil.isNotNull(stockNo), "采购申请仓库编号不能为空");
                stockNoList.add(stockNo);
                // 设置采购申请号 仓库编号 请购数量
                sendDetail.setSumNo(purDtl.getRequestNo());
                sendDetail.setStockNo(stockNo);
                sendDetail.setPurQty(purDtl.getQty());
                // 设置采购订单 收货数量 和 满单标识
                Double qty = purDtl.getRecQty() + sendDetail.getQty();
                purDtl.setRecQty(qty);
                // 供应商
                sendDetail.setVendorNo(sendHead.getProviderNo());

                if (qty >= purDtl.getQty()) {
                    purDtl.setStatus(true);
                }

                // 设置到货日期 到货号
                sendDetail.setRecDate(sendHead.getPurchaseInDate());
                sendDetail.setSendNo(sendHead.getSendNo());
                purMapper.updatePurRecQty(purDtl);
            }

            stockNoList = stockNoList.stream().distinct().collect(Collectors.toList());
            if (stockNoList.size() > 0) {
                sendHead.setStockNo(stockNoList.get(0));
            }
        }

        sendMapper.insertBatchHdr(list);
        list.forEach(sendHead -> sendMapper.insertBatchDtl(sendHead.getSendDetailList()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "ZZTransactionManager")
    public void deleteBatch(List<String> billCodes) throws InterFaceException {

        for (String billCode : billCodes) {
            checkDeleteSendService.check(billCode, "存在入库单");
        }

        sendMapper.deleteBatchHdr(billCodes);
        sendMapper.deleteBatchDtl(billCodes);
    }
}

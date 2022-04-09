package com.hf.project.dye.entity.purchaseplan;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.OrderHdrBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Description 采购订单主表
 * @Author fyq
 * @Date 2021/5/7 15:52
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class PurchasePlanHdr extends OrderHdrBase implements Serializable {

    /** GUID */
    @JSONField(deserialize = false)
    private String guid = IdUtil.randomUUID();

    /** 请购公司编码 */
    @JSONField(deserialize = false)
    private Integer companyID;

    /** 请购公司名称 */
    @JSONField(name = "companyName")
    @NotBlank(message = "请购公司不能为空")
    private String companyName;

    /** 原始供应商编码 */
    @JSONField(name = "orgProviderNo")
    @NotBlank(message = "原始供应商编码不能为空")
    private String orgProviderNo;

    /** 原始供应商GUID */
    @JSONField(deserialize = false)
    private String orgProviderGUID;

    /** 原始供应商名称 */
    @JSONField(deserialize = false)
    private String orgProviderName;

    /** 运输方式 */
    @JSONField(name = "trafficMode")
    private String trafficMode;

//    /** 采购订单号 */
//    @JSONField(name = "planNo")
//    @NotBlank(message = "采购订单号不能为空")
//    private String planNo;

//    /** 采购订单下单日期 */
//    @JSONField(name = "planDate", format = "yyyy-MM-dd")
//    @NotNull(message = "采购订单下单日期不能为空")
//    private Date planDate;

//    /** 采购员编号 */
//    @JSONField(name = "buyerNo")
//    @NotBlank(message = "采购员编号不能为空")
//    private String buyerNo;

    /** 采购员姓名 */
    @JSONField(deserialize = false)
    private String buyerName;

    /** 采购员GUID */
    @JSONField(deserialize = false)
    private String buyersGUID;

//    /** 供应商编号 */
//    @JSONField(name = "providerNo")
//    @NotBlank(message = "供应商编码不能为空")
//    private String providerNo;

    /** 供应商名字 */
    @JSONField(deserialize = false)
    private String providerName;

    /** 供应商GUID */
    @JSONField(deserialize = false)
    private String providerGUID;

//    /** 币种 */
//    @JSONField(name = "currency")
//    @NotBlank(message = "币种不能为空")
//    private String currency;
//
//    /** 汇率 */
//    @JSONField(name = "exchangeRate")
//    @NotNull(message = "汇率不能为空")
//    private Double exchangeRate;

    /** 采购类型 内购 or 外购 */
    @JSONField(name = "contractType")
    @NotBlank(message = "采购类型不能为空")
    private String contractType;

    /** 采购类型名称 内购 or 外购 */
    @JSONField(name = "contractTypeName")
    @NotBlank(message = "采购类型名称不能为空")
    private String contractTypeName;

    /** 开票方式 */
    @JSONField(name = "invoiceType")
    @NotBlank(message = "开票方式不能为空")
    private String invoiceType;

    /** 开票方式名称 */
    @JSONField(name = "invoiceTypeName")
    @NotBlank(message = "开票方式名称不能为空")
    private String invoiceTypeName;

    /** 付款方式 */
    @JSONField(name = "paymentMode")
    @NotBlank(message = "付款方式不能为空")
    private String paymentMode;

    /** 付款方式名称 */
    @JSONField(name = "paymentModeName")
    @NotBlank(message = "付款方式名称不能为空")
    private String paymentModeName;

    /** 环思主从配标识 否则无法查询到 化工 = 616
     * 		606 607
     * 		616 613
     *
     * */
    @JSONField(deserialize = false)
    private Integer noteTypeID;

    /** 状态 默认 2 */
    @JSONField(deserialize = false)
    private Integer billStatus = 2;

    @JSONField(name = "items")
    @NotNull(message = "采购订单明细不能为空")
    @Valid
    private List<PurchasePlanDtl> purchasePlanDtlList;
}

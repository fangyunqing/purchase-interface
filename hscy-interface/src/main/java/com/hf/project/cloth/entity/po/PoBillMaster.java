package com.hf.project.cloth.entity.po;

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
 * @Description 订单主表
 * @Author fyq
 * @Date 2021/9/8 8:38
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class PoBillMaster extends OrderHdrBase implements Serializable {

    /** 主键 */
    @JSONField(deserialize = false)
    private Integer id;

    /** 请购公司 */
    @JSONField(deserialize = false)
    private Integer companyId = 1;

//    /** 采购订单号 */
//    @JSONField(name = "planNo")
//    @NotBlank(message = "采购订单号不能为空")
//    private String poNo;

//    /** 采购员编码 */
//    @JSONField(name = "buyerNo")
//    @NotBlank(message = "采购员编号不能为空")
//    private String purchaserId;

//    /** 供应商编号 */
//    @JSONField(name = "providerNo")
//    @NotBlank(message = "供应商编码不能为空")
//    private String supplierCode;

    /** 供应商ID */
    @JSONField(deserialize = false)
    private Integer supplierId;

    /** 供应商名称 */
    @JSONField(deserialize = false)
    private String supplierName;

//    /** 币种 */
//    @JSONField(name = "currency")
//    @NotBlank(message = "币种不能为空")
//    private String currency;

    /** 币种ID */
    @JSONField(deserialize = false)
    private Integer currencyId;

//    /** 汇率 */
//    @JSONField(name = "exchangeRate")
//    @NotNull(message = "汇率不能为空")
//    private Double exchangeRate;

//    /** 采购类型 内购 or 外购 */
//    @JSONField(name = "contractType")
//    private String contractType;

    /** 开票方式 */
    @JSONField(name = "invoiceType")
    @NotBlank(message = "开票方式不能为空")
    private String invoiceType;

    /** 开票方式名称 */
    @JSONField(name = "invoiceTypeName")
    @NotBlank(message = "开票方式名称不能为空")
    private String invoiceTypeName;

    /** 开票类型ID */
    @JSONField(deserialize = false)
    private Integer invoiceId;

    /** 付款方式 */
    @JSONField(name = "paymentMode")
    @NotBlank(message = "付款方式不能为空")
    private String paymentMode;

    /** 付款方式名称 */
    @JSONField(name = "paymentModeName")
    @NotBlank(message = "付款方式名称不能为空")
    private String paymentModeName;

    /** 付款方式编码 */
    @JSONField(deserialize = false)
    private String paymentCode;

    /** 仓库ID */
    @JSONField(deserialize = false)
    private Integer storeId;

    /** 价格条款 */
    @JSONField(name = "priceTerm")
    @NotBlank(message = "价格条款不能为空")
    private String priceTerm;

    /** 价格条款名称 */
    @JSONField(name = "priceTermName")
    @NotBlank(message = "价格条款名称不能为空")
    private String priceTermName;

    /** 价格条款 Code */
    @JSONField(deserialize = false)
    private String priceTermCode;

    /** 运输方式 */
    @JSONField(name = "trafficMode")
    private String deliveryMethod;

    /** 付款账期 */
    @JSONField(name = "payDay")
    private String payDay = "0";

    /** 交货地点 */
    @JSONField(name = "deliverySite")
    private String deliverySite;

    /** 物料类型 面料Y or 辅料F 根据物料表判断 */
    @JSONField(deserialize = false)
    private String materialType;

    /** 状态 默认 2 */
    @JSONField(deserialize = false)
    private Integer billStatus = 2;

    /** 预付款 */
    @JSONField(deserialize = false)
    private Double prepayAmount = 0.0;

    @JSONField(name = "items")
    @NotNull(message = "采购订单明细不能为空")
    @Valid
    private List<PoBillDetail> poBillDetails;
}

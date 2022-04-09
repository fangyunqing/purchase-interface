package com.hf.project.zz.entity.pur;

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
 * @Date 2021/10/12 11:36
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class PurHdr extends OrderHdrBase implements Serializable {

    /** 公司代码 */
    @JSONField(deserialize = false)
    private String cpID = "HF";

    /** 审核标识 默认 审核 */
    @JSONField(deserialize = false)
    private Boolean flag = true;

//    /** 采购订单号 */
//    @JSONField(name = "planNo")
//    @NotBlank(message = "采购订单号不能为空")
//    private String purNo;

//    /** 采购订单下单日期 */
//    @JSONField(name = "planDate", format = "yyyy-MM-dd")
//    @NotNull(message = "采购订单下单日期不能为空")
//    private Date purDate;

//    /** 采购员编号 */
//    @JSONField(name = "buyerNo")
//    @NotBlank(message = "采购员编号不能为空")
//    private String buyerNo;

    /** 采购员姓名 */
    @JSONField(deserialize = false)
    private String buyerName;

//    /** 供应商编号 */
//    @JSONField(name = "providerNo")
//    @NotBlank(message = "供应商编码不能为空")
//    private String supplier;

    /** 供应商名字 */
    @JSONField(deserialize = false)
    private String supplierName;

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

    /** 订单明细 */
    @JSONField(name = "items")
    @NotNull(message = "采购订单明细不能为空")
    @Valid
    private List<PurDtl> purDtlList;

}

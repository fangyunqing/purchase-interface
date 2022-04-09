package com.hf.project.lt.entity.matorder;

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
 * @Date 2021/9/22 14:21
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class MatOrderHdr extends OrderHdrBase implements Serializable {

//    /** 采购订单号 */
//    @JSONField(name = "planNo")
//    @NotBlank(message = "采购订单号不能为空")
//    private String orderNo;

    /** 采购政策 默认 C*/
    @JSONField(deserialize = false)
    private String policyID = "C";

//    /**供应商编码 */
//    @JSONField(name = "providerNo")
//    @NotBlank(message = "供应商编码不能为空")
//    private String vendermID;

    /** 采购部门 默认  */
    @JSONField(deserialize = false)
    private String cgDept = "HMBM4";

//    /** 币种 */
//    @JSONField(name = "currency")
//    @NotBlank(message = "币种不能为空")
//    private String purHb;
//
//    /** 汇率 */
//    @JSONField(name = "exchangeRate")
//    @NotNull(message = "汇率不能为空")
//    private Double hl;

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

    /** 交货方式 */
    @JSONField(name = "deliveryType")
    @NotBlank(message = "交货方式不能为空")
    private String deliveryType;

    /** 交货方式名称 */
    @JSONField(name = "deliveryTypeName")
    @NotBlank(message = "交货方式名称不能为空")
    private String deliveryTypeName;

    /** 厂别 */
    @JSONField(deserialize = false)
    private String facNo = "A";

    /** 采购类别 */
    @JSONField(deserialize = false)
    private String cgKind = "G01";

    /** 交货地址 */
    @JSONField(deserialize = false)
    private String JhAdr = "福建省莆田市荔城区北高镇坑园村岭头268号";

    /** 审核标识 默认为1*/
    @JSONField(deserialize = false)
    private Integer checkFlag = 1;

    /** 付款情况 默认为1*/
    @JSONField(deserialize = false)
    private Integer payFlag = 1;

    /** 订单明细 */
    @JSONField(name = "items")
    @NotNull(message = "采购订单明细不能为空")
    @Valid
    private List<MatOrderDtl> matOrderDtls;



}

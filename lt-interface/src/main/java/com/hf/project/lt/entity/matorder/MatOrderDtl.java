package com.hf.project.lt.entity.matorder;

import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.OrderDtlBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description 到货订单明细表
 * @Author fyq
 * @Date 2021/9/22 14:21
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class MatOrderDtl extends OrderDtlBase implements Serializable {

    /** 采购订单号 */
    @JSONField(deserialize = false)
    private String orderNo;

    /** 采购订单明细行号 */
    @JSONField(deserialize = false)
    private String item;

//    /** 采购云订单明细主键 */
//    @JSONField(name = "BIPItemCode")
//    @NotNull(message = "采购云订单明细主键不能为空")
//    private String itemCode;

//    /** 物料编号 */
//    @JSONField(name = "materialNo")
//    @NotBlank(message = "物料编码不能为空")
//    private String matNo;

//    /** 数量 */
//    @JSONField(name = "qty")
//    @NotNull(message = "数量不能为空")
//    private Double cgQty;
//
//    /** 单位 */
//    @JSONField(name = "unit")
//    @NotBlank(message = "单位不能为空")
//    private String unit;

    /** cg单位 */
    @JSONField(deserialize = false)
    private String cgUnit;

//    /** 单价 */
//    @JSONField(name = "price")
//    @NotNull(message = "单价不能为空")
//    private Double mPrice;
//
//    /** 金额 */
//    @JSONField(name = "amount")
//    @NotNull(message = "金额不能为空")
//    private Double taxAmount;

//    /** 计划交期 */
//    @JSONField(name = "deliveryDate", format = "yyyy-MM-dd")
//    @NotNull(message = "计划交期不能为空")
//    private Date deliveryDate;

//    /** 未税单价 */
//    @JSONField(name = "noTaxPrice")
//    @NotNull(message = "未税单价不能为空")
//    private Double bPrice;

    /** 可超交比例 */
    @JSONField(name = "limitPer")
    @NotBlank(message = "可超交比例不能为空")
    private String limitPer;

    /** 序号 */
    @JSONField(deserialize = false)
    private String sortNo;


//    /** 采购申请明细主键 */
//    @JSONField(name = "requestItemCode")
//    @NotBlank(message = "采购申请明细主键不能为空")
//    private String requestItemCode;
//
//    /** 采购申请单号 */
//    @JSONField(name = "requestNo")
//    @NotBlank(message = "采购申请单号不能为空")
//    private String requestNo;

}

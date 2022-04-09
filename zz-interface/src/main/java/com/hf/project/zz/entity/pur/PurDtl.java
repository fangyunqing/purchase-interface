package com.hf.project.zz.entity.pur;

import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.OrderDtlBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 采购订单明细
 * @Author fyq
 * @Date 2021/10/12 13:33
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class PurDtl extends OrderDtlBase implements Serializable {

    /** 采购订单号 */
    @JSONField(deserialize = false)
    private String purNo;

    /** 是否满单 */
    @JSONField(deserialize = false)
    private Boolean status = false;

    /** 等级 */
    @JSONField(name = "grade")
    @NotBlank(message = "等级不能为空")
    private String grade;

//    /** 采购申请单号 */
//    @JSONField(name = "requestNo")
//    @NotBlank(message = "采购申请单号不能为空")
//    private String reqNo;
//
//    /** 采购申请明细主键 */
//    @JSONField(name = "requestItemCode")
//    @NotBlank(message = "采购申请明细主键不能为空")
//    private String requestItemCode;

//    /** 物料编号 */
//    @JSONField(name = "materialNo")
//    @NotBlank(message = "物料编码不能为空")
//    private String mtlNo;

    /** 物料名称 */
    @JSONField(deserialize = false)
    private String mtlName;

    /** 物料规格 */
    @JSONField(deserialize = false)
    private String mtlSpec;

//    /** 数量 */
//    @JSONField(name = "qty")
//    @NotNull(message = "数量不能为空")
//    private Double qty;
//
//    /** 单位 */
//    @JSONField(serialize = false)
//    private String unit = "KG";

    /** 粒数 */
    @JSONField(name = "qtyEx")
    @NotNull(message = "扩展数量(粒数)不能为空")
    private Double pkgQty;

//    /** 计划交期 */
//    @JSONField(name = "deliveryDate", format = "yyyy-MM-dd")
//    @NotNull(message = "计划交期不能为空")
//    private Date etc;

    /** 到货数量 */
    @JSONField(deserialize = false)
    private Double recQty = 0.00;

//    /** 采购云订单明细主键 */
//    @JSONField(name = "BIPItemCode")
//    @NotNull(message = "采购云订单明细主键不能为空")
//    private String itemCode;
}

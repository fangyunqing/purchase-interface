package com.hf.project.dye.entity.purchaseplan;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.OrderDtlBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 采购订单子表
 * @Author fyq
 * @Date 2021/5/7 16:26
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class PurchasePlanDtl extends OrderDtlBase implements Serializable {

    /** GUID */
    @JSONField(deserialize = false)
    private String guid = IdUtil.randomUUID();

    /** 主表 GUID */
    @JSONField(deserialize = false)
    private String parentGUID;

//    /** 采购云订单明细主键 */
//    @JSONField(name = "BIPItemCode")
//    @NotNull(message = "采购云订单明细主键不能为空")
//    private String itemCode;
//
//    /** 采购申请单号 */
//    @JSONField(name = "requestNo")
//    @NotBlank(message = "采购申请单号不能为空")
//    private String requestNo;

//    /** 采购申请明细主键 */
//    @JSONField(name = "requestItemCode")
//    @NotBlank(message = "采购申请明细主键不能为空")
//    private String requestItemCode;

//    /** 物料编号 */
//    @JSONField(name = "materialNo")
//    @NotBlank(message = "物料编码不能为空")
//    private String materialNo;

    /** 物料名称 */
    @JSONField(deserialize = false)
    private String materialName;

    /** 物料GUID */
    @JSONField(deserialize = false)
    private String materialGUID;

//    /** 数量 */
//    @JSONField(name = "qty")
//    @NotNull(message = "数量不能为空")
//    private Double qty;
//
//    /** 单位 */
//    @JSONField(name = "unit")
//    @NotBlank(message = "单位不能为空")
//    private String unit;


//    /** 计划交期 */
//    @JSONField(name = "deliveryDate", format = "yyyy-MM-dd")
//    @NotNull(message = "计划交期不能为空")
//    private Date deliveryDate;

}

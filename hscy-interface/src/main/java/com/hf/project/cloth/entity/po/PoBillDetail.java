package com.hf.project.cloth.entity.po;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.OrderDtlBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description 订单明细表
 * @Author fyq
 * @Date 2021/9/8 8:39
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class PoBillDetail extends OrderDtlBase implements Serializable {

    /** ID */
    @JSONField(deserialize = false)
    private Integer id;

//    /** 采购云订单明细主键 */
//    @JSONField(name = "BIPItemCode")
//    @NotNull(message = "采购云订单明细主键不能为空")
//    private String itemCode;

    /** 采购订单主表ID */
    @JSONField(deserialize = false)
    private Integer poBillMasterId;

//    /** 数量 */
//    @JSONField(name = "qty")
//    @NotNull(message = "数量不能为空")
//    private Double qty;
//
//    /** 单位 */
//    @JSONField(name = "unit")
//    @NotBlank(message = "单位不能为空")
//    private String unit;

    /** 单位ID */
    @JSONField(deserialize = false)
    private Integer unitId;

//    /** 计划交期 */
//    @JSONField(name = "deliveryDate", format = "yyyy-MM-dd")
//    @NotNull(message = "计划交期不能为空")
//    private Date deliveryDate;

    /** GUID */
    @JSONField(deserialize = false)
    private String guid = IdUtil.randomUUID();

    /** 规格 */
    @JSONField(name = "component")
//    @NotBlank(message = "规格不能为空")
    private String realModel;

//    /** 采购申请明细主键 */
//    @JSONField(name = "requestItemCode")
//    @NotBlank(message = "采购申请明细主键不能为空")
//    private String poBillRequestDtlID;
//
//    /** 采购申请单号 */
//    @JSONField(name = "requestNo")
//    @NotBlank(message = "采购申请单号不能为空")
//    private String requestNo;

//    /** 物料编号 */
//    @JSONField(name = "materialNo")
//    @NotBlank(message = "物料编码不能为空")
//    private String materialNo;

    /** 物料ID */
    @JSONField(deserialize = false)
    private Integer materialId;

    /** MRP 物料ID */
    @JSONField(deserialize = false)
    private Integer sdMrpPoMaterialId;

    /** 订单号 */
    @JSONField(deserialize = false)
    private String orderNo;

    /** 款号 */
    @JSONField(serialize = false)
    private String styleNo;

    /** 产品ID */
    @JSONField(deserialize = false)
    private String productNo;

    /** MRP 标准需求量 */
    @JSONField(deserialize = false)
    private Double MRPQty;

    /** MRP 单位 */
    @JSONField(deserialize = false)
    private Integer MRPUnitId;

    /** 固定采购加成数 */
    @JSONField(deserialize = false)
    private Double fixedWastage;

    /** 损耗 */
    @JSONField(deserialize = false)
    private Double wastageRate;

    /** 转换率 */
    @JSONField(deserialize = false)
    private Double unitConvertRate;

    /** 克重 */
    @JSONField(deserialize = false)
    private String gmm;

    /** 幅宽 */
    @JSONField(deserialize = false)
    private String width;

    /** 款号 */
    @JSONField(deserialize = false)
    private String lotNo;


}

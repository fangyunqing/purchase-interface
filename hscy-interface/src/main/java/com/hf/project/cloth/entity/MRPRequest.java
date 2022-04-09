package com.hf.project.cloth.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Description MRP
 * @Author fyq
 * @Date 2021/9/9 15:16
 **/

@Data
public class MRPRequest {

    /** MRP 物料ID */
    @JSONField(serialize = false)
    private Integer sdMrpPoMaterialId;

    /** 订单号 */
    @JSONField(serialize = false)
    private String orderNo;

    /** 款号 */
    @JSONField(serialize = false)
    private String styleNo;

    /** 产品ID */
    @JSONField(serialize = false)
    private String productNo;

    /** MRP 标准需求量 */
    @JSONField(serialize = false)
    private Double MRPQty;

    /** MRP 单位 */
    @JSONField(serialize = false)
    private Integer MRPUnitId;

    /** 固定采购加成数 */
    @JSONField(serialize = false)
    private Double fixedWastage;

    /** 损耗 */
    @JSONField(serialize = false)
    private Double wastageRate;

    /** 转换率 */
    @JSONField(serialize = false)
    private Double unitConvertRate;

}

package com.hf.project.common.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description 到货明细
 * @Author fyq
 * @Date 2021/10/20 15:43
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class ArrivalDtlBase extends PurchaseMoney {

    /** 采购云到货明细主键 */
    @JSONField(name = "BIPItemId")
    @NotNull(message = "采购云到货明细主键不能为空")
    private String BIPItemId;

    /** 采购云到货明细行号 */
    @JSONField(name = "BIPItemCode")
    @NotNull(message = "采购云到货明细行号不能为空")
    private String BIPItemCode;

    /** 物料编码 */
    @JSONField(name = "materialNo")
    @NotBlank(message = "物料编码不能为空")
    private String materialNo;

    /** 数量 */
    @JSONField(name = "qty")
    @NotNull(message = "数量不能为空")
    private Double qty;

    /** 单位 */
    @JSONField(name = "unit")
    @NotBlank(message = "单位不能为空")
    private String unit;

    /** 单位名称 */
    @JSONField(name = "unitName")
    @NotBlank(message = "单位名称不能为空")
    private String unitName;

    /** 采购订单号 */
    @JSONField(name = "planNo")
    @NotBlank(message = "采购订单号不能为空")
    private String planNo;

    /** 采购云订单明细主键 */
    @JSONField(name = "PlanItemId")
    @NotBlank(message = "采购订单明细主键不能为空")
    private String planItemId;

    /** 采购云订单明细行号 */
    @JSONField(name = "PlanItemCode")
    @NotBlank(message = "采购订单明细行号不能为空")
    private String planItemCode;


}

package com.hf.project.dye.entity.commonpurchasein;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.ArrivalDtlBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description 到货明细表
 * @Author fyq
 * @Date 2021/5/7 17:12
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class CommonPurchaseInDtl extends ArrivalDtlBase implements Serializable {

    /** GUID */
    @JSONField(deserialize = false)
    private String guid = IdUtil.randomUUID();

    /** 主表的GUID */
    @JSONField(deserialize = false)
    private String parentGUID;

//    /** 采购云到货明细主键 */
//    @JSONField(name = "BIPItemCode")
//    @NotNull(message = "采购云到货明细主键不能为空")
//    private String itemCode;

    /** 扩展数量 */
    @JSONField(name = "qtyEx")
    private Double inPkgExQty;

    /** 扩展单位 */
    @JSONField(name = "unitEx")
    private String pkgUnitEx;

    /** 扩展单位名称 */
    @JSONField(name = "unitExName")
    private String pkgUnitExName;

//    /** 数量 */
//    @JSONField(name = "qty")
//    @NotNull(message = "数量不能为空")
//    private Double qty;

    /** 批次号 */
    @JSONField(name = "materialLot")
    @NotBlank(message = "批次号不能为空")
    private String materialLot;

    /** 物料名称 */
    @JSONField(deserialize = false)
    private String materialName;

//    /** 物料编码 */
//    @JSONField(name = "materialNo")
//    @NotBlank(message = "物料编码不能为空")
//    private String materialNo;

    /** 物料GUID */
    @JSONField(deserialize = false)
    private String materialGUID;

//    /** 采购订单号 */
//    @JSONField(name = "planNo")
//    @NotBlank(message = "采购订单号不能为空")
//    private String planNo;

    /** 采购订单GUID */
    @JSONField(deserialize = false)
    private String purchasePlanDtlGUID;

//    /** 单位 */
//    @JSONField(name = "unit")
//    @NotBlank(message = "单位不能为空")
//    private String unit;

//    /** 采购云订单明细主键 */
//    @JSONField(name = "BIPPlanItemCode")
//    @NotBlank(message = "采购订单明细主键不能为空")
//    private String planItemCode;


}

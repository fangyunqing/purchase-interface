package com.hf.project.cloth.entity.poin;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.ArrivalDtlBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yaml.snakeyaml.events.Event;

import java.io.Serializable;

/**
 * @Description 采购到货
 * @Author fyq
 * @Date 2021/9/8 17:08
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class CommonPoBillInDtl extends ArrivalDtlBase implements Serializable {

    /** ID */
    @JSONField(deserialize = false)
    private Integer id;

    /** GUID */
    @JSONField(deserialize = false)
    private String guid = IdUtil.randomUUID();

//    /** 采购云到货明细主键 */
//    @JSONField(name = "BIPItemCode")
//    @NotNull(message = "采购云到货明细主键不能为空")
//    private String itemCode;

    /** 主表 ID */
    @JSONField(deserialize = false)
    private Integer pobillInHdrID;

//    /** 物料编号 */
//    @JSONField(name = "materialNo")
//    @NotBlank(message = "物料编码不能为空")
//    private String materialNo;

    /** 物料ID */
    @JSONField(deserialize = false)
    private Integer materialId;

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

//    /** 采购订单号 */
//    @JSONField(name = "planNo")
//    @NotBlank(message = "采购订单号不能为空")
//    private String planNo;
//
//    /** 采购云订单明细主键 */
//    @JSONField(name = "BIPPlanItemCode")
//    @NotBlank(message = "采购订单明细主键不能为空")
//    private String planItemCode;

    /** 采购订单明细 ID */
    @JSONField(deserialize = false)
    private Integer poBillDtlID;
}

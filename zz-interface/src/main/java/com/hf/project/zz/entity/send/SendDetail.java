package com.hf.project.zz.entity.send;

import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.ArrivalDtlBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 采购到货子表
 * @Author fyq
 * @Date 2021/10/13 13:47
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class SendDetail extends ArrivalDtlBase implements Serializable {

    /** 到货号 */
    @JSONField(deserialize = false)
    private Long sendNo;

//    /** 采购订单号 */
//    @JSONField(name = "planNo")
//    @NotBlank(message = "采购订单号不能为空")
//    private String purNo;
//
//    /** 采购云订单明细主键 */
//    @JSONField(name = "BIPPlanItemCode")
//    @NotBlank(message = "采购订单明细主键不能为空")
//    private String purItemCode;

    /** 请购单号 */
    @JSONField(deserialize = false)
    private String sumNo;

//    /** 物料编号 */
//    @JSONField(name = "materialNo")
//    @NotBlank(message = "物料编码不能为空")
//    private String mtlNo;

    /** 采购数量 */
    @JSONField(deserialize = false)
    private Double purQty;

//    /** 数量 */
//    @JSONField(name = "qty")
//    @NotNull(message = "数量不能为空")
//    private Double qty;

    /** 粒数 */
    @JSONField(name = "qtyEx")
    @NotNull(message = "扩展数量(粒数)不能为空")
    private Double pkgQty;

//    /** 单位 */
//    @JSONField(name = "unit")
//    @NotBlank(message = "单位不能为空")
//    private String unit;

    /** 粒数单位 */
    @JSONField(name = "unitEx")
    @NotBlank(message = "扩展单位(粒数单位)不能为空")
    private String unit2;

    /** 粒数单位名称 */
    @JSONField(name = "unitExName")
    @NotBlank(message = "扩展单位(粒数单位)不能为空")
    private String unitName2;

    /** 批次号 */
    @JSONField(name = "materialLot")
    @NotBlank(message = "批次号不能为空")
    private String mtlLot;

    /** 等级 */
    @JSONField(name = "grade")
    @NotBlank(message = "等级不能为空")
    private String grade;

    /** 仓库编号 */
    @JSONField(deserialize = false)
    private String stockNo;

    /** 收货日期 */
    @JSONField(deserialize = false)
    private Date recDate;

    /** 审核状态 默认 Y  */
    @JSONField(deserialize = false)
    private String flag = "N";

    /** 供应商编号 */
    @JSONField(deserialize = false)
    private String vendorNo;

//    /** 采购云到货明细主键 */
//    @JSONField(name = "BIPItemCode")
//    @NotNull(message = "采购云到货明细主键不能为空")
//    private String itemCode;
}

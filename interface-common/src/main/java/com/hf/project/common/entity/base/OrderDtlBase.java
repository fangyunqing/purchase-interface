package com.hf.project.common.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 订单明细表
 * @Author fyq
 * @Date 2021/10/20 15:22
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDtlBase extends PurchaseMoney {

    /** 采购云订单明细主键 */
    @JSONField(name = "BIPItemId")
    @NotNull(message = "采购云订单明细主键不能为空")
    private String BIPItemId;

    /** 采购云订单明细行号 */
    @JSONField(name = "BIPItemCode")
    @NotNull(message = "采购云订单明细行号不能为空")
    private String BIPItemCode;

    /** 物料编号 */
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

    /** 计划交期 */
    @JSONField(name = "deliveryDate", format = "yyyy-MM-dd")
    @NotNull(message = "计划交期不能为空")
    private Date deliveryDate;

    /** 采购申请单号 */
    @JSONField(name = "requestNo")
    @NotBlank(message = "采购申请单号不能为空")
    private String requestNo;

    /** 采购申请明细行号 */
    @JSONField(name = "requestItemCode")
    @NotBlank(message = "采购申请明细行号不能为空")
    private String requestItemCode;

    /** 采购申请明细主键 */
    @JSONField(name = "requestItemId")
    @NotBlank(message = "采购申请单明细主键不能为空")
    private String requestItemId;



}

package com.hf.project.cloth.entity.purchasecontract;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 采购合同明细表
 * @Author fyq
 * @Date 2021/9/3 17:01
 **/

@Data
public class PurchaseContractDtl {

    /** GUID */
    @JSONField(serialize = false)
    private String guid = IdUtil.randomUUID();

    /** 父GUID */
    @JSONField(serialize = false)
    private String parentGUID;

    /** 物料号 */
    @JSONField(name = "materialcode")
    @NotNull(message = "物料号不能为空")
    private String materialNo;

    /** 物料名称 */
    @JSONField(serialize = false)
    private String materialName;

    /** 物料GUID */
    @JSONField(serialize = false)
    private String materialGUID;

    /** 数量 */
    @JSONField(name = "num")
    @NotNull(message = "数量不能为空")
    private Double qty;

    /** 单位 */
    @JSONField(serialize = false)
    private String unit;

    /** 单价 */
    @JSONField(name = "price")
    @NotNull(message = "单价不能为空")
    private Double price;

    /** 折扣率 */
    @JSONField(serialize = false)
    private Double discountRate = 1.0;

    /** 金额 */
    @JSONField(name = "money")
    @NotNull(message = "金额不能为空")
    private Double amount;
}

package com.hf.project.common.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 金额
 * @Author fyq
 * @Date 2021/10/14 15:58
 **/

@Data
public class PurchaseMoney {

    /** 未含税单价 */
    @JSONField(name = "price")
    @NotNull(message = "未含税单价不能为空")
    private Double price;

    /** 未含税金额 */
    @JSONField(name = "amount")
    @NotNull(message = "未含税金额不能为空")
    private Double amount;

    /** 含税单价 */
    @JSONField(name = "taxPrice")
    @NotNull(message = "含税单价不能为空")
    private Double taxPrice;

    /** 含税金额 */
    @JSONField(name = "taxAmount")
    @NotNull(message = "含税金额不能为空")
    private Double taxAmount;

    /** 税率 */
    @JSONField(name = "taxRate")
    @NotNull(message = "税率不能为空")
    private Double taxRate;
}

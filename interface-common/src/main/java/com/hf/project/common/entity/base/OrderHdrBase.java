package com.hf.project.common.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 订单主表
 * @Author fyq
 * @Date 2021/10/20 15:00
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderHdrBase extends Operator {

    /** 采购订单号 */
    @JSONField(name = "planNo")
    @NotBlank(message = "采购订单号不能为空")
    private String planNo;

    /** 采购订单下单日期 */
    @JSONField(name = "planDate", format = "yyyy-MM-dd")
    @NotNull(message = "采购订单下单日期不能为空")
    private Date planDate;

    /** 采购员编号 */
    @JSONField(name = "buyerNo")
    @NotBlank(message = "采购员编号不能为空")
    private String buyerNo;

    /** 供应商编号 */
    @JSONField(name = "providerNo")
    @NotBlank(message = "供应商编码不能为空")
    private String providerNo;

    /** 币种 */
    @JSONField(name = "currency")
    @NotBlank(message = "币种不能为空")
    private String currency;

    /** 币种名称 */
    @JSONField(name = "currencyName")
    @NotBlank(message = "币种名称不能为空")
    private String currencyName;

    /** 汇率 */
    private String exchangeRate;

}

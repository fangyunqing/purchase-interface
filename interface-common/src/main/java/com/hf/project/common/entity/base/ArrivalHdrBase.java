package com.hf.project.common.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 到货主表
 * @Author fyq
 * @Date 2021/10/20 15:36
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class ArrivalHdrBase extends Operator {

    /** 到货号 */
    @JSONField(name = "purchaseInNo")
    @NotBlank(message = "到货单号不能为空")
    private String purchaseInNo;

    /** 到货日期 */
    @JSONField(name = "purchaseInDate", format = "yyyy-MM-dd")
    @NotNull(message = "到货日期不能为空")
    private Date purchaseInDate;

    /** 供应商 */
    @JSONField(name = "providerNo")
    @NotBlank(message = "供应商编码不能为空")
    private String providerNo;

    /** 地磅毛重 */
    @JSONField(name = "grossWeight")
    private Double grossWeight;

    /** 地磅净重 */
    @JSONField(name = "netWeight")
    private Double netWeight;

    /** 地磅单号 */
    @JSONField(name = "weightBridgeCode")
    private String weightBridgeCode;

    /** 车牌号 */
    @JSONField(name = "carNo")
    private String carNo;


}

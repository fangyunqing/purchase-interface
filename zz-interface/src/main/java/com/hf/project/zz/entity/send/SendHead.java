package com.hf.project.zz.entity.send;

import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.ArrivalHdrBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Description 采购出货主表
 * @Author fyq
 * @Date 2021/10/13 13:29
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class SendHead extends ArrivalHdrBase implements Serializable {
//
//    /** 地磅毛重 */
//    @JSONField(name = "grossWeight")
//    private Double grossWeight;
//
//    /** 地磅净重 */
//    @JSONField(name = "netWeight")
//    private Double netWeight;
//
//    /** 地磅单号 */
//    @JSONField(name = "weighbridgeCode")
//    private String weightNo;
//
//    /** 车牌 */
//    @JSONField(name = "carNo")
//    private String carNo;

//    /** 供应商编码 */
//    @JSONField(name = "providerNo")
//    @NotBlank(message = "供应商编码不能为空")
//    private String supplier;

    /** 序列 */
    @JSONField(deserialize = false)
    private Long sendNo;

    /** 供应商名字 */
    @JSONField(deserialize = false)
    private String supplierName;

    /** 原始供应商编码 */
    @JSONField(name = "orgProviderNo")
    @NotBlank(message = "原始供应商编码不能为空")
    private String orgSupplier;

    /** 原始供应商名字 */
    @JSONField(deserialize = false)
    private String orgSupplierName;

    /** 仓库编号 */
    @JSONField(deserialize = false)
    private String stockNo;

    /** 审核标识 默认 审核 */
    @JSONField(deserialize = false)
    private Boolean flag = true;

//    /** 到货单号 */
//    @JSONField(name = "purchaseInNo")
//    @NotBlank(message = "到货单号不能为空")
//    private String sendNo;
//
//    /** 到货日期 */
//    @JSONField(name = "purchaseInDate", format = "yyyy-MM-dd")
//    @NotNull(message = "到货日期不能为空")
//    private Date sendDate;

    /** 订单明细 */
    @JSONField(name = "items")
    @NotNull(message = "采购订单明细不能为空")
    @Valid
    private List<SendDetail> sendDetailList;
}

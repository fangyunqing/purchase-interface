package com.hf.project.cloth.entity.poin;

import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.ArrivalHdrBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Description 到货单主表
 * @Author fyq
 * @Date 2021/9/8 16:00
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class CommonPoBillInHdr extends ArrivalHdrBase implements Serializable {

    /** ID */
    @JSONField(deserialize = false)
    private Integer id;

//    /** 到货号 */
//    @JSONField(name = "purchaseInNo")
//    @NotBlank(message = "到货单号不能为空")
//    private String pobillInNo;

//    /** 供应商编号 */
//    @JSONField(name = "providerNo")
//    @NotBlank(message = "供应商编码不能为空")
//    private String supplierCode;

    /** 供应商ID */
    @JSONField(deserialize = false)
    private Integer supplierId;

    /** 供应商名称 */
    @JSONField(deserialize = false)
    private String supplierName;

//    /** 到货日期 */
//    @JSONField(name = "purchaseInDate", format = "yyyy-MM-dd")
//    @NotNull(message = "到货日期不能为空")
//    private Date poBillInDate;

    /** 状态 默认 2 */
    @JSONField(deserialize = false)
    private Integer billStatus = 2;

    @JSONField(name = "items")
    @NotNull(message = "采购到货明细不能为空")
    @Valid
    private List<CommonPoBillInDtl> commonPoBillInDtls;



}

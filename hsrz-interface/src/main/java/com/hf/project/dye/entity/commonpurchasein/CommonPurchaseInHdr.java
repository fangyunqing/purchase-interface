package com.hf.project.dye.entity.commonpurchasein;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.ArrivalHdrBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * @Description 到货主表
 * @Author fyq
 * @Date 2021/5/7 16:48
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class CommonPurchaseInHdr extends ArrivalHdrBase implements Serializable {

    /** GUID */
    @JSONField(deserialize = false)
    private String guid = IdUtil.randomUUID();

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
//    private String weighbridgeCode;

//    /** 供应商 */
//    @JSONField(name = "providerNo")
//    @NotBlank(message = "供应商编码不能为空")
//    private String providerNo;

    /** 供应商名字 */
    @JSONField(deserialize = false)
    private String providerName;

    /** 供应商GUID */
    @JSONField(deserialize = false)
    private String providerGUID;

    /** 仓库名称 */
    @JSONField(deserialize = false)
    private String storeName;

    /** 仓库名称 */
    @JSONField(deserialize = false)
    private String storeNo;

    /** 仓库GUID */
    @JSONField(deserialize = false)
    private String storeGUID;


    /** 采购类型  */
    @JSONField(deserialize = false)
    private String type;

//    /** 到货号 */
//    @JSONField(name = "purchaseInNo")
//    @NotBlank(message = "到货单号不能为空")
//    private String purchaseInNo;

//    /** 到货日期 */
//    @JSONField(name = "purchaseInDate", format = "yyyy-MM-dd")
//    @NotNull(message = "到货日期不能为空")
//    private Date purchaseInDate;

    /** 节点 */
    @JSONField(deserialize = false)
    private Integer noteTypeID;

    /** 状态 默认 2 */
    @JSONField(deserialize = false)
    private Integer billStatus = 2;

    /** 明细 */
    @JSONField(name = "items")
    @Valid
    private List<CommonPurchaseInDtl> commonPurchaseInDtlList;
}

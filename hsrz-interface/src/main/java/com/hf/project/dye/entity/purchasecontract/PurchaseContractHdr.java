package com.hf.project.dye.entity.purchasecontract;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.hf.project.common.entity.base.Operator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description 采购合同主表
 * @Author fyq
 * @Date 2021/8/27 10:49
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class PurchaseContractHdr extends Operator implements Serializable {

    /** 主键 */
    @JSONField(serialize = false)
    private String guid = IdUtil.randomUUID();

    /** 合同号 */
    @JSONField(name = "billno")
    @NotBlank(message = "合同号不能为空")
    private String contractNo;

    /** 合同类型 */
    @JSONField(name = "billtype")
    private String contractType;

    /** 采购组织 */
    @JSONField(name = "org_name")
    private String orgName;

    /** 采购组织编码 */
    @JSONField(name = "org_code")
    private String orgCode;

    /** 公司ID */
    @JSONField(serialize = false)
    private Integer companyID;

    /** 供应商编码 */
    @JSONField(name = "supplier_code")
    @NotBlank(message = "供应商编码不能为空")
    private String providerNo;

    /** 供应商 GUID */
    @JSONField(serialize = false)
    private String providerGUID;

    /** 供应商名称 */
    @JSONField(serialize = false)
    private String providerName;

    /** 合同日期 */
    @JSONField(name = "contractDate", format = "yyyy-MM-dd")
    @NotNull(message = "合同日期能为空")
    private Date contractDate;

    /** 开始时间 */
    @JSONField(name = "actualvalidate")
    private Date startDate;

    /** 结束时间 */
    @JSONField(name = "actualinvalidate")
    private Date endDate;

    /** 是否有效 */
    @JSONField(serialize = false)
    private Boolean usable = true;

    /** 状态 默认 2 */
    @JSONField(serialize = false)
    private Integer billStatus = 2;

    @JSONField(name = "items")
    @NotNull(message = "采购合同明细不能为空")
    @Valid
    private List<PurchaseContractDtl> purchaseContractDtlList;



}

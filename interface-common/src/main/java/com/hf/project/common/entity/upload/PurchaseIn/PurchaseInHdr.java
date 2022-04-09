package com.hf.project.common.entity.upload.PurchaseIn;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description 入库主表
 * @Author fyq
 * @Date 2021/5/7 14:42
 **/

@Data
public class PurchaseInHdr implements Serializable {

    /** 入库单号 */
    @JSONField(name = "vbillcode")
    @NotBlank(message = "入库单号不能为空")
    private String billCode;

    /** 供应商编码 */
    @JSONField(name = "suppliercode")
    @NotBlank(message = "供应商编码不能为空")
    private String supplierCode;

    /** 仓库编码 */
    @JSONField(name = "warehousecode")
    @NotBlank(message = "仓库编码不能为空")
    private String wareHouseCode;

    /** 单据日期 */
    @JSONField(name = "dbilldate",format = "yyyy-MM-dd")
    @NotNull(message = "单据日期不能为空")
    private Date billDate;

    /** 采购部门编码 */
    @JSONField(name = "cdeptcode")
    private String deptCode;

    /** 采购组织名称 */
    @JSONField(name = "cpurorgname")
    @NotBlank(message = "采购组织名称不能为空")
    private String purOrgName;

    // 备注
    @JSONField(name = "memo")
    private String memo;

    /** 审核人 反审核人 */
    @JSONField(serialize = false)
    private String auditMan;

    @Valid
    @JSONField(name = "items")
    private List<PurchaseInDtl> purchaseInDtlList;
}

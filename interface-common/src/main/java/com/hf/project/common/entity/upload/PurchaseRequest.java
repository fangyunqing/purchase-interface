package com.hf.project.common.entity.upload;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 采购申请
 * @Author fyq
 * @Date 2021/5/7 14:04
 **/

@Data
public class PurchaseRequest implements Serializable {

    /** 采购申请号 */
    @JSONField(name = "vbillcode")
    @NotBlank(message = "采购申请号不能为空")
    private String billCode;

    /** 采购组织名称  companyFullName */
    @JSONField(name = "puorgname")
    @NotBlank(message = "采购组织名称不能为空")
    private String purOraName;

    /** 采购申请明细行号 */
    @JSONField(name = "itemcode")
    @NotNull(message = "采购申请明细行号不能为空")
    private String itemCode;

    /** 采购申请明细主键 */
    @JSONField(name = "itemid")
    @NotNull(message = "采购申请明细主键不能为空")
    private String itemId;

    /** 物料编码 */
    @JSONField(name = "materialcode")
    @NotBlank(message = "物料编码不能为空")
    private String materialCode;

    /** 物料型号 */
    @JSONField(name = "materialmodel")
    private String materialModel;

    /** 物料规格 */
    @JSONField(name = "materialspec")
    private String materialSpec;

    /** 单位编码 */
    @JSONField(name = "materialunitcode")
    @NotBlank(message = "单位不能为空")
    private String materialUnitCode;

    /** 收货地址  */
    @JSONField(name = "planpsnreceiveaddress")
    @NotBlank(message = "收货地址不能为空")
    private String planPersonReceiveAddress;

    /** 收货组织名称 */
    @JSONField(name = "receiveorgname")
    @NotBlank(message = "收货组织名称不能为空")
    private String receiveOrgName;

    /** 收货人编码 */
    @JSONField(name = "receivepersoncode")
    private String receivePersonCode;

    /** 收货人联系电话 */
    @JSONField(name = "receivepersontel")
    private String receivePersonTel;

    /** 需求人编码 */
    @JSONField(name = "reqcontactcode")
    @NotBlank(message = "需求人编码不能为空")
    private String reqContactCode;

    /** 需求时间 */
    @JSONField(name = "reqdate",format = "yyyy-MM-dd HH:mm:ss")
    private Date reqDate;

    /** 计划员编码 */
    @JSONField(name = "planpsncode")
    private String planPsnCode;

    /** 计划部门编码 */
    @JSONField(name = "planpsndeptcode")
    private String planPersonDeptCode;

    /** 建议供应商编码 */
    @JSONField(name = "suggestsuppliercode")
    private String suggestSupplierCode;

    /** 需求组织名称 */
    @JSONField(name = "planpsnorgname")
    @NotBlank(message = "需求组织名称不能为空")
    private String planPsnOrgName;

    /** 请求数量 */
    @JSONField(name = "materialamounts")
    @NotNull(message = "请求数量不能为空")
    private Double materialAmounts;

    /** 等级 */
    @JSONField(name = "grade")
    private String grade;

    /** 备注 */
    @JSONField(name = "memo")
    private String memo;

    /** 自定义 */
    @JSONField(serialize = false)
    private String define1;
}

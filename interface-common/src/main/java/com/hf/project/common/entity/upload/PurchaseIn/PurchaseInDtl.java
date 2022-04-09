package com.hf.project.common.entity.upload.PurchaseIn;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description 入库明细
 * @Author fyq
 * @Date 2021/5/7 14:46
 **/

@Data
public class PurchaseInDtl {

    /** 物料编码 */
    @JSONField(name = "materialcode")
    @NotBlank(message = "物料编码不能为空")
    private String materialCode;

    /** 单位编码 */
    @JSONField(name = "cunitcode")
    @NotBlank(message = "单位编码不能为空")
    private String unitCode;

    /** 扣税类别 默认 => 1*/
    @JSONField(name = "ftaxtypeflag")
    @NotNull(message = "扣税类别不能为空")
    private Integer taxTypeFlag = 1;

    /** 实收数量 */
    @JSONField(name = "nnum")
    @NotNull(message = "实收数量不能为空")
    private Double num;

    /** 应收数量 */
    @JSONField(name = "nshoulnum")
    private Double shoulNum;

    /** 无税金额 */
    @JSONField(name = "norigmny")
    @NotNull(message = "无税金额不能为空")
    private Double origMoney;

    /** 价税合计 */
    @JSONField(name = "norigtaxmny")
    @NotNull(message = "价税合计不能为空")
    private Double origTaxMoney;

    /** 含税单价 */
    @JSONField(name = "nqtorigtaxprice")
    @NotNull(message = "含税单价不能为空")
    private Double origTaxPrice;

    /** 无税单价 */
    @JSONField(name = "nqtorigprice")
    @NotNull(message = "无税单价不能为空")
    private Double origPrice;

    /** 来源到货单号 */
    @JSONField(name = "sourcecode")
    @NotBlank(message = "来源到货单号不能为空")
    private String sourceCode;

    /** 来源到货行ID */
    @JSONField(name = "sourcebodyid")
    @NotBlank(message = "来源到货行ID不能为空")
    private String sourceBodyId;


}

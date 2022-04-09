package com.hf.project.common.entity.upload;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description  物料
 * @Author fyq
 * @Date 2021/8/25 16:29
 **/

@Data
public class Material implements Serializable {

    /** 主键 */
    @JSONField(serialize = false)
    private String id;

    /** 第二主键 */
    @JSONField(serialize = false)
    private String iid;

    /** 物料编码  默认会加上前缀*/
    @JSONField(name = "code")
    @NotBlank(message = "物料编码不能为空")
    private String code;

    /** 物料名称 */
    @JSONField(name = "name")
    @NotBlank(message = "物料名称不能为空")
    private String name;

    /** 规格 */
    @JSONField(name = "materialspec")
    private String materialSpec;

    /** 型号 */
    @JSONField(name = "materialtype")
    private String materialType;

    /** NC 计量单位 */
    @JSONField(name = "measdoc")
    @NotBlank(message = "计量单位不能为空")
    private String measdoc;

    /** 本地单位 */
    @JSONField(serialize = false)
    private String unit;

    /** 产品线 */
    @JSONField(name = "prodline")
    private String prodLine;

    /** 品牌 */
    @JSONField(name = "brand")
    private String brand;

    /** 产地 */
    @JSONField(name = "prodarea")
    private String prodArea;

    /** 产品生命周期 */
    @JSONField(name = "prolifeperiod")
    private String proLifePeriod;

    /** 长度 */
    @JSONField(name = "unitlength")
    private String unitLength;

    /** 高度 */
    @JSONField(name = "unitheight")
    private String unitHeight;

    /** 宽度 */
    @JSONField(name = "unitwidth")
    private String unitWidth;

    /** 体积 */
    @JSONField(name = "unitvolume")
    private String unitVolume;

    /** 重量 */
    @JSONField(name = "unitweight")
    private String unitWeight;

    /** 税类 默认 一般纳税商品*/
    @JSONField(name = "mattaxes")
    private String mattaxes = "CN001";

    /** 克重 */
    @JSONField(serialize = false)
    private String gmm;

    /** 本地分类 */
    @JSONField(serialize = false)
    private String localType;

    /**入库容差 */
    @JSONField(name = "intolerance")
    private Integer inTolerance = 10;

    /** 出库容差 */
    @JSONField(name = "outtolerance")
    private Integer outTolerance = 10;

    /** 物料分类 */
    @JSONField(name = "marbasclass")
    @NotBlank(message = "物料分类不能为空")
    private String marBasClass;

    /** 物料分类ID */
    @JSONField(serialize = false)
    private String marBasClassId;

    /** 自定义1 */
    @JSONField(serialize = false)
    private String define1;

}

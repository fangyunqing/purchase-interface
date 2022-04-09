package com.hf.project.dye.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 供应商
 * @Author fyq
 * @Date 2021/5/8 8:23
 **/

@Data
public class Provider implements Serializable {

    /** GUID */
    @JSONField(deserialize = false)
    private String guid = IdUtil.randomUUID();;

    /** 供应商编码 */
    @JSONField(name = "code")
    @NotBlank(message = "供应商编码不能为空")
    private String providerNo;

    /** 供应商名称 */
    @JSONField(name = "name")
    @NotBlank(message = "供应商编码不能为空")
    private String providerName;

    /** 供应商分类 默认为 1 */
    @JSONField(deserialize = false)
    private String providerType = "1";

    /** 供应商分类GUID 默认为 7A3F1CDF-77F1-4A8D-A4C5-035B4C634E46 */
    @JSONField(deserialize = false)
    private String providerTypeGUID = "7A3F1CDF-77F1-4A8D-A4C5-035B4C634E46";

    /** 公司ID 默认 1 => 新材料 */
    @JSONField(deserialize = false)
    private Integer companyID = 1;

    /** NC供应商字段 */
    @JSONField(deserialize = false)
    private String NCProviderNo;

    /** 是否可用 */
    @JSONField(deserialize = false)
    private Boolean usable = true;

    /** 创建人 默认NC*/
    @JSONField(deserialize = false)
    private String creator = "NC";

    /** 创建时间 */
    @JSONField(serialize = false)
    private Date createTime = DateUtil.date();

}

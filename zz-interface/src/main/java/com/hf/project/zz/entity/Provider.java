package com.hf.project.zz.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Description 织造供应商
 * @Author fyq
 * @Date 2021/10/11 14:26
 **/

@Data
public class Provider {

    /** 供应商编码 */
    @JSONField(name = "code")
    @NotBlank(message = "供应商编码不能为空")
    private String providerNo;

    /** 供应商编码 */
    @JSONField(name = "name")
    @NotBlank(message = "供应商名称不能为空")
    private String providerName;

    /** 供应商分类 */
    @JSONField(name = "supplyTypeValue")
    private String type;

    /** 供应商地址 */
    @JSONField(name = "corpAddress")
    private String address;

    /** 同步类型 */
    @JSONField(deserialize = false)
    private String syncType = "采购云同步";

    @JSONField(deserialize = false)
    private Boolean flag = true;

    @JSONField(deserialize = false)
    private Date sysDate = DateUtil.date();

    @JSONField(deserialize = false)
    private String guid = IdUtil.randomUUID();
}

package com.hf.project.common.entity.base;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 操作人员
 * @Author fyq
 * @Date 2021/9/4 9:44
 **/

@Data
public class Operator {

    /** 创建人 */
    @JSONField(name = "creator")
    @NotBlank(message = "创建人不能为空")
    private String creator;

    /** 创建时间 */
    @JSONField(name = "createTime", format = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "创建时间不能为空")
    private Date createTime;

    /** 更新人 */
    @JSONField(serialize = false)
    private String updateMan = "BIP";

    /** 更新时间 */
    @JSONField(serialize = false)
    private Date updateTime = DateUtil.date();

    /** 审核人 */
    @JSONField(name = "confirmMan")
    @NotBlank(message = "审核人不能为空")
    private String confirmMan;

    /** 审核时间 */
    @JSONField(name = "confirmTime", format = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "审核时间不能为空")
    private Date confirmTime;
}

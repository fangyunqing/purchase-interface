package com.hf.project.common.entity.properties;

import lombok.Data;

/**
 * @Description 必要参数
 * @Author fyq
 * @Date 2021/8/24 13:36
 **/

@Data
public class RequiredProperties {

    /** 组织名称 */
    private String orgName = "福建华峰集团";

    /** 系统编码 */
    private String sysCode;

    /** 系统名称 */
    private String sysName;

    /** 租户ID */
    private String tenantId;

    /** 没有子表明细时是否抛出异常 */
    private Boolean throwNoSuchException = false;

    /** 维护人员邮箱 */
    private String[] maintenanceStaffMail;

    /** 成功是否发送邮件 针对操作人员 */
    private Boolean successMail = false;

    /** 失败是否发送邮件 针对操作人员 */
    private Boolean failMail =  false;

    /** 失败是否发送邮件 针对维护人员 */
    private Boolean failMaintenanceStaffMail = false;

    /** 测试模式 不推送到NC或者BIP  */
    private Boolean testMode =  false;

    /** 是否需要采购合同 */
    private Boolean purchaseContract = true;

    /** 字典如果不存在是否新增 */
    private Boolean dictInsert = true;
}

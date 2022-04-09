package com.hf.project.common.util;

import com.hf.project.common.entity.common.CommonDeleteTenant;
import com.hf.project.common.entity.common.CommonDisableTenant;
import com.hf.project.common.entity.common.CommonInsertTenant;
import com.hf.project.common.entity.common.CommonParameterTenant;
import com.hf.project.common.entity.properties.RequiredProperties;

/**
 * @Description 通用类生产
 * @Author fyq
 * @Date 2021/9/2 13:05
 **/

public class CommonObjectUtil {

    public static CommonParameterTenant getCommonDeleteTenant(Object object, RequiredProperties requiredProperties) {

        CommonDeleteTenant commonDeleteTenant = new CommonDeleteTenant<>(object);
        commonDeleteTenant.setOrgName(requiredProperties.getOrgName());
        commonDeleteTenant.setSysCode(requiredProperties.getSysCode());
        commonDeleteTenant.setTenantId(requiredProperties.getTenantId());

        return commonDeleteTenant;
    }

    public static CommonParameterTenant getCommonDisableTenant(Object object, RequiredProperties requiredProperties) {

        CommonDisableTenant commonDisableTenant = new CommonDisableTenant<>(object);
        commonDisableTenant.setOrgName(requiredProperties.getOrgName());
        commonDisableTenant.setSysCode(requiredProperties.getSysCode());
        commonDisableTenant.setTenantId(requiredProperties.getTenantId());

        return commonDisableTenant;
    }

    public static CommonParameterTenant getCommonInsertTenant(Object object, RequiredProperties requiredProperties) {

        CommonInsertTenant commonInsertTenant = new CommonInsertTenant<>(object);
        commonInsertTenant.setOrgName(requiredProperties.getOrgName());
        commonInsertTenant.setSysCode(requiredProperties.getSysCode());
        commonInsertTenant.setTenantId(requiredProperties.getTenantId());

        return commonInsertTenant;
    }

}

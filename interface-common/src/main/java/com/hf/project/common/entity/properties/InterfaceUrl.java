package com.hf.project.common.entity.properties;

import lombok.Data;

/**
 * @Description 接口地址
 * @Author fyq
 * @Date 2021/8/24 13:42
 **/

@Data
public class InterfaceUrl {

    /** 查询用户信息接口地址 */
    private String userUrl;

    /** 物料保存接口地址 */
    private String saveMaterialUrl;

    /** 物料停用接口地址 */
    private String disableMaterialUrl;

    /** 采购需求新增接口地址 */
    private String insertPurchaseReqUrl;

    /** 采购需求删除接口地址 */
    private String deletePurchaseReqUrl;

    /** 采购入库单新增接口地址 */
    private String insertPurchaseInUrl;

    /** 采购入库单删除接口地址 */
    private String deletePurchaseInUrl;
}

package com.hf.project.dye.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description 染整 NoteType
 * @Author fyq
 * @Date 22/04/09 9:21
 **/

@Data
@AllArgsConstructor
public class BillNoteType {

    /** 类型 */
    private String typeName;

    /** 采购申请ID */
    private Integer requestNoteTypeId;

    /** 采购订单ID */
    private Integer purchasePlanNoteTypeId;

    /** 采购到货ID */
    private Integer commonPurchaseInNoteTypeId;

}

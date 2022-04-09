package com.hf.project.dye.util;

import com.hf.project.dye.entity.BillNoteType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 染整 申请 订单 到货
 * @Author fyq
 * @Date 22/04/09 9:18
 **/

@Component
public class BillNoteTypeUtil {

    private static final List<BillNoteType> BILL_NOTE_TYPE_LIST = new ArrayList<>();

    static {
        BILL_NOTE_TYPE_LIST.add(new BillNoteType("化工采购", 606, 616, 170501109));
        BILL_NOTE_TYPE_LIST.add(new BillNoteType("坯布采购", 607, 613, 170501108));
    }

    public BillNoteType queryByPurchasePlan(Integer purchasePlanNoteTypeId) {
        return BILL_NOTE_TYPE_LIST.stream()
                .filter(billNoteType -> billNoteType.getPurchasePlanNoteTypeId().equals(purchasePlanNoteTypeId))
                .findFirst().orElse(null);
    }

    public BillNoteType queryByRequest(Integer requestNoteTypeId) {
        return BILL_NOTE_TYPE_LIST.stream()
                .filter(billNoteType -> billNoteType.getRequestNoteTypeId().equals(requestNoteTypeId))
                .findFirst().orElse(null);
    }

    public BillNoteType queryByCommonPurchaseIn(Integer commonPurchaseInNoteTypeId) {
        return BILL_NOTE_TYPE_LIST.stream()
                .filter(billNoteType -> billNoteType.getCommonPurchaseInNoteTypeId().equals(commonPurchaseInNoteTypeId))
                .findFirst().orElse(null);
    }
}

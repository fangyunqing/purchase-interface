package com.hf.project.common.mapper.datadict;

import com.hf.project.common.datadict.IDataDict;
import com.hf.project.common.entity.BaseData;
import com.hf.project.common.entity.enums.BaseDataType;
import com.sun.mail.imap.protocol.ID;

public interface MaterialUnitMapper extends IDataDict {

    @Override
    default BaseDataType getBaseDataType() {
        return BaseDataType.MATERIAL_UNIT;
    }

}

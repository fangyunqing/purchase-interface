package com.hf.project.common.mapper.datadict;

import com.hf.project.common.datadict.IDataDict;
import com.hf.project.common.entity.enums.BaseDataType;

public interface ContractTypeMapper extends IDataDict {

    @Override
    default BaseDataType getBaseDataType() {
        return BaseDataType.CONTRACT_TYPE;
    }
}

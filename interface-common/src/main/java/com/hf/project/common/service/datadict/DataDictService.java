package com.hf.project.common.service.datadict;

import cn.hutool.core.util.ObjectUtil;
import com.hf.project.common.datadict.IDataDict;
import com.hf.project.common.entity.BaseData;
import com.hf.project.common.entity.enums.BaseDataType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.exception.NoSuchBaseDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 字典服务类
 * @Author fyq
 * @Date 2021/11/22 11:26
 **/

@Service
public final class DataDictService {

    @Autowired
    private List<IDataDict> dataDictList;

    @Autowired
    private RequiredProperties requiredProperties;

    public BaseData query(BaseData baseData, Boolean insert, Boolean IfNullThrowException, BaseDataType baseDataType)
            throws NoSuchBaseDataException {

        for (IDataDict iDataDict : dataDictList) {

            if (iDataDict.getBaseDataType() == baseDataType) {

                BaseData bd = iDataDict.query(baseData.getNCCode());
                if (ObjectUtil.isNotNull(bd)) {
                    return bd;
                }
                else {

                    if (insert) {
                        iDataDict.insert(baseData);
                        return baseData;
                    }
                    else if (IfNullThrowException) {
                        throw new NoSuchBaseDataException(requiredProperties.getSysName(),
                                baseDataType.getDescription(),
                                baseData.getNCCode());
                    }

                }

            }
        }

        throw new NoSuchBaseDataException(requiredProperties.getSysName(), null, null);

    }

}

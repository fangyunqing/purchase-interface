package com.hf.project.common.datadict;

import com.hf.project.common.entity.BaseData;
import com.hf.project.common.entity.enums.BaseDataType;

public interface IDataDict {

    /**
     * @Author fyq
     * @Description 返回货币信息
     * @Date 9:55 2021/9/8
     * @Param [NCCode]
     * @return com.hf.project.common.entity.BaseData
     **/
    BaseData query(String NCCode);

    /**
     * @Author fyq
     * @Description 新增一条数据
     * @Date 9:34 2021/11/15
     * @Param [baseData]
     * @return void
     **/
    void insert(BaseData baseData);

    /**
     * @Author fyq
     * @Description 返回类型
     * @Date 11:31 2021/11/22
     * @Param
     * @return
     **/
    BaseDataType getBaseDataType();
}

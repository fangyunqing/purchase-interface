package com.hf.project.dye.mapper;

import com.hf.project.dye.entity.Buyer;

public interface BuyerMapper {

    /**
     * @Author fyq
     * @Description 查询采购员信息
     * @Date 10:21 2021/9/3
     * @Param [buyerNo]
     * @return com.hf.project.dye.entity.Buyer
     **/
    Buyer query(String buyerNo);

    /**
     * @Author fyq
     * @Description 插入一条新的
     * @Date 14:25 2021/11/15
     * @Param [buyer]
     * @return void
     **/
    void insert(Buyer buyer);

}

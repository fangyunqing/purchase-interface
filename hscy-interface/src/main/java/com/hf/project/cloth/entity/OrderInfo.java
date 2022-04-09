package com.hf.project.cloth.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description 订单模块
 * @Author fyq
 * @Date 2021/11/17 10:26
 **/

@Data
public class OrderInfo {

    /** sLotNo */
    private String lotNo;

    /** sContractNo */
    private String contractNo;

    /** dDeliveryDate */
    private Date deliveryDate;

    /** sProductNo */
    private String productNo;

    /** iCustomerId */
    private Integer customerId;

    /** sCustPoNo */
    private String custPoNo;

    /** nInseam */
    private Integer inseam;

}

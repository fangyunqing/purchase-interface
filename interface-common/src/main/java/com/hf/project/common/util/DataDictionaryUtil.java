package com.hf.project.common.util;

import cn.hutool.core.util.ObjectUtil;
import com.hf.project.common.entity.BaseData;
import com.hf.project.common.entity.enums.BaseDataType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.exception.DiscordException;
import com.hf.project.common.exception.NoSuchBaseDataException;
import com.hf.project.common.mapper.common.MaterialMapper;
import com.hf.project.common.mapper.datadict.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 字典处理
 * @Author fyq
 * @Date 2021/11/15 11:30
 **/

@Component
public class DataDictionaryUtil {

    @Autowired
    private PriceTermMapper priceTermMapper;

    @Autowired
    private CurrencyMapper currencyMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private MaterialUnitMapper materialUnitMapper;

    @Autowired
    private PaymentModeMapper paymentModeMapper;

    @Autowired
    private InvoiceTypeMapper invoiceTypeMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    public Material getMaterial(String code) throws DiscordException {

        Material material = materialMapper.querySingle(code);
        if (ObjectUtil.isNotNull(material)){
            return material;
        }
        else {
            throw new DiscordException(requiredProperties.getSysName(),
                    DiscordException.DISCORD_MATERIAL,
                    code);
        }
    }

    public BaseData getPriceTerm(String code, String name, Integer id) throws NoSuchBaseDataException {

        BaseData baseData = priceTermMapper.query(code);
        if (ObjectUtil.isNotNull(baseData)) {
            return baseData;
        }
        else {

            if (requiredProperties.getDictInsert()) {

                baseData = new BaseData();
                if (ObjectUtil.isNotNull(id)) {
                    baseData.setId(id.toString());
                }
                baseData.setCode(code);
                baseData.setName(name);
                baseData.setNCCode(code);
                priceTermMapper.insert(baseData);

                return baseData;

            }
            else {
                throw new NoSuchBaseDataException(requiredProperties.getSysName(),
                        BaseDataType.PRICE_TERM.getDescription(),
                        code);
            }

        }
    }

    public BaseData getPriceTerm(String code, String name) throws NoSuchBaseDataException {
        return getPriceTerm(code, name, null);
    }

    public BaseData getCurrency(String code) throws NoSuchBaseDataException {

        BaseData baseData = currencyMapper.query(code);
        if (ObjectUtil.isNotNull(baseData)) {
            return baseData;
        }
        else {

            throw new NoSuchBaseDataException(requiredProperties.getSysName(),
                    BaseDataType.CURRENCY.getDescription(),
                    code);
        }

    }

    public BaseData getMaterialUnit(String code, String name, Integer id, Boolean insert) throws NoSuchBaseDataException {

        BaseData baseData = materialUnitMapper.query(code);
        if (ObjectUtil.isNotNull(baseData)) {
            return baseData;
        }
        else {

            if (requiredProperties.getDictInsert() && insert) {

                baseData = new BaseData();
                if (ObjectUtil.isNotNull(id)) {
                    baseData.setId(id.toString());
                }
                baseData.setCode(code);
                baseData.setName(name);
                baseData.setNCCode(code);
                materialUnitMapper.insert(baseData);

                return baseData;
            }
            else {
                throw new NoSuchBaseDataException(requiredProperties.getSysName(),
                        BaseDataType.MATERIAL_UNIT.getDescription(),
                        code);
            }

        }

    }

    public BaseData getMaterialUnit(String code, String name, Boolean insert) throws NoSuchBaseDataException {
        return getMaterialUnit(code, name, null, insert);
    }

    public BaseData getPaymentMode(String code, String name, Integer id) throws NoSuchBaseDataException {

        BaseData baseData = paymentModeMapper.query(code);
        if (ObjectUtil.isNotNull(baseData)) {
            return baseData;
        }
        else {

            if (requiredProperties.getDictInsert()) {

                baseData = new BaseData();
                if (ObjectUtil.isNotNull(id)) {
                    baseData.setId(id.toString());
                }
                baseData.setCode(code);
                baseData.setName(name);
                baseData.setNCCode(code);
                paymentModeMapper.insert(baseData);

                return baseData;
            }
            else {
                throw new NoSuchBaseDataException(requiredProperties.getSysName(),
                        BaseDataType.PAYMENT_MODE.getDescription(),
                        code);
            }


        }
    }

    public BaseData getPaymentMode(String code, String name) throws NoSuchBaseDataException {
        return getPaymentMode(code, name, null);
    }

    public BaseData getInvoiceType(String code, String name, Integer id) throws NoSuchBaseDataException {

        BaseData baseData = invoiceTypeMapper.query(code);
        if (ObjectUtil.isNotNull(baseData)) {
            return baseData;
        }
        else {

            if (requiredProperties.getDictInsert()) {

                baseData = new BaseData();
                if (ObjectUtil.isNotNull(id)) {
                    baseData.setId(id.toString());
                }
                baseData.setCode(code);
                baseData.setName(name);
                baseData.setNCCode(code);
                invoiceTypeMapper.insert(baseData);

                return baseData;

            }
            else {
                throw new NoSuchBaseDataException(requiredProperties.getSysName(),
                        BaseDataType.INVOICE_TYPE.getDescription(),
                        code);
            }


        }

    }

    public BaseData getInvoiceType(String code, String name) throws NoSuchBaseDataException {
        return getInvoiceType(code, name, null);
    }
}

package com.hf.project.dye.handler.provider;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonInsert;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.SupplierService;
import com.hf.project.dye.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 供应商插入
 * @Author fyq
 * @Date 2021/8/27 10:17
 **/

@Component("supplierInsert")
public class ProviderInsertHandler extends AbstractValidIHandleData<CommonInsert<List<Provider>>> {

    @Autowired
    private SupplierService<Provider> supplierService;

    @Override
    protected CommonInsert<List<Provider>> parseJson(String json) {
          return JSONObject.parseObject(json, new TypeReference<CommonInsert<List<Provider>>>(){});
    }

    @Override
    protected CommonOutput doHandleData(CommonInsert<List<Provider>> listCommonInsert) throws InterFaceException {

        supplierService.insertBatch(listCommonInsert.getData());

        return new CommonOutput(1,"");

    }
}

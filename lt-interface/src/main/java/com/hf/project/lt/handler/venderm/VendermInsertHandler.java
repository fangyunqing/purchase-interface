package com.hf.project.lt.handler.venderm;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonInsert;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.SupplierService;
import com.hf.project.lt.entity.MatVenderm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 供应商插入
 * @Author fyq
 * @Date 2021/9/20 15:47
 **/

@Component("supplierInsert")
public class VendermInsertHandler extends AbstractValidIHandleData<CommonInsert<List<MatVenderm>>> {

    @Autowired
    private SupplierService<MatVenderm> supplierService;

    @Override
    protected CommonInsert<List<MatVenderm>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonInsert<List<MatVenderm>>>(){});
    }

    @Override
    protected CommonOutput doHandleData(CommonInsert<List<MatVenderm>> listCommonInsert) throws InterFaceException {

        supplierService.insertBatch(listCommonInsert.getData());

        return new CommonOutput(1,"");
    }
}

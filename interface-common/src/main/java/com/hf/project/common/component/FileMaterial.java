package com.hf.project.common.component;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hf.project.common.entity.MaterialParam;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Description 读取物料参数
 * @Author fyq
 * @Date 2021/10/16 15:54
 **/

@Component
public class FileMaterial {

    private static final String FILE_NAME = "init_material.json";

    public MaterialParam read() {

        try {
            try (FileReader fileReader = new FileReader(FILE_NAME)) {

                JSONReader jsonReader = new JSONReader(fileReader);
                return jsonReader.readObject(MaterialParam.class);
            }
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public void write(MaterialParam materialParam) throws IOException {

        try (FileWriter fileWriter = new FileWriter(FILE_NAME)){
            JSONObject.writeJSONString(fileWriter, materialParam, SerializerFeature.PrettyFormat);
        }
    }

}

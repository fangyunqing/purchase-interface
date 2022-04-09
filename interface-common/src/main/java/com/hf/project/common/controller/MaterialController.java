package com.hf.project.common.controller;

import com.hf.project.common.upload.impl.material.MaterialDisableUpload;
import com.hf.project.common.upload.impl.material.MaterialSaveUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description 物料
 * @Author fyq
 * @Date 2021/8/30 8:32
 **/

@RestController
@RequestMapping("/material")
@ConditionalOnProperty(prefix = "purchasing-cloud.request-property",value = "material", havingValue = "true",matchIfMissing = true)
public class MaterialController {

    @Autowired
    private MaterialDisableUpload materialDisableUpload;

    @Autowired
    private MaterialSaveUpload materialSaveUpload;

    @GetMapping("/disable")
    public String MaterialDisable(String codes, String workCode) {
        return materialDisableUpload.upLoad(codes,workCode);
    }

    @GetMapping("/save")
    public String MaterialSave(String codes, String workCode) {
        return materialSaveUpload.upLoad(codes,workCode);
    }

    @PostMapping("/postSave")
    public String materialPostSave(@RequestBody Map<String, String> map) {
        String codes = map.getOrDefault("codes","");
        String workCode = map.getOrDefault("workCode", "");
        return materialSaveUpload.upLoad(codes, workCode);
    }
}

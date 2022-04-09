package com.hf.project.zz.schedule;

import com.hf.project.common.component.FileMaterial;
import com.hf.project.common.helper.MailHelper;
import com.hf.project.common.service.MaterialService;
import com.hf.project.common.upload.impl.material.MaterialSaveUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

/**
 * @Description 织造定时
 * @Author fyq
 * @Date 2021/10/18 8:13
 *
 * 由于织造的物料是环思同步过来和同时物料不需要加前缀 所有去除定时 以及系统启动物料上传
 **/

//@EnableScheduling
//@Slf4j
//@Component
public class ZZSchedule {

    @Autowired
    private FileMaterial fileMaterial;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialSaveUpload materialSaveUpload;

    @Autowired
    private MailHelper mailHelper;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void materialSchedule() throws IOException {

//        MaterialParam materialParam = fileMaterial.read();
//        assert materialParam != null;
//
//        Date preSync = ObjectUtil.isNotNull(materialParam.getSyncDate()) ? materialParam.getSyncDate() : materialParam.getUploadDate();
//        Date syncDate = DateUtil.date();
//        assert preSync != null;
//        List<String> materials = materialService.querySync(preSync);
//        if (materials.size() == 0)
//            return;
//
//        String res = materialSaveUpload.upLoad(StrUtil.join(",", materials), null);
//        if (res.equals("1")) {
//            materialParam.setSyncDate(syncDate);
//            fileMaterial.write(materialParam);
//        }
//        else {
//            log.error(res);
//            mailHelper.sendInvokeExceptionMail(res);
//        }
    }

}

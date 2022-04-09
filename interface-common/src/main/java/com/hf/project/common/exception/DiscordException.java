package com.hf.project.common.exception;


import cn.hutool.core.util.StrUtil;

/**
 * @Description 基础资料两边不一致
 * @Author fyq
 * @Date 2021/5/8 17:05
 **/

public class DiscordException extends InterFaceException implements SendMail  {

    public static final String DISCORD_MATERIAL = "物料档案";

    public static final String DISCORD_USER = "用户档案";

    public static final String DISCORD_PROVIDER = "供应商档案";


    public DiscordException(String sysName, String discordType, String discordNo){

        super(StrUtil.format("{}-{}[{}]未存在",
                StrUtil.blankToDefault(sysName, "未知系统"),
                StrUtil.blankToDefault(discordType, "未知档案"),
                StrUtil.blankToDefault(discordNo, "未知档案编号")
                ));

    }

}

package cn.iocoder.yudao.module.smartcity.controller.admin.guideinformationinput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 指南信息录入新增/修改 Request VO")
@Data
public class GuideInformationInputSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4280")
    private Long id;

    @Schema(description = "指南名称", example = "张三")
    private String guideName;

    @Schema(description = "适用范围")
    private String scopeOfApplication;

    @Schema(description = "发布单位")
    private String publishingUnit;

    @Schema(description = "发布日期")
    private String releaseDate;

    @Schema(description = "更新日期")
    private String updateDate;

    @Schema(description = "主要内容概述")
    private String mainContentOverview;

}
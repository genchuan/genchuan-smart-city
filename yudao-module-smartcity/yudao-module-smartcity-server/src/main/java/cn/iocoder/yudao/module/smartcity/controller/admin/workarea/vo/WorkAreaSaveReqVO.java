package cn.iocoder.yudao.module.smartcity.controller.admin.workarea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 作业区域新增/修改 Request VO")
@Data
public class WorkAreaSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14576")
    private Long id;

    @Schema(description = "区域编号")
    private String areaNumber;

    @Schema(description = "区域名称", example = "李四")
    private String areaName;

    @Schema(description = "区域位置")
    private String regionalLocation;

    @Schema(description = "区域面积")
    private String regionalArea;

    @Schema(description = "人员容量")
    private String personnelCapacity;

    @Schema(description = "环境风险因素")
    private String environmentalFactors;

    @Schema(description = "防护措施要求")
    private String requirementsMeasures;

}
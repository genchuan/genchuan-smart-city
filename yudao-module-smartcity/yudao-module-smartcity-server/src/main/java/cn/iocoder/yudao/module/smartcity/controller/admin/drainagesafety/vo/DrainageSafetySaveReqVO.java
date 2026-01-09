package cn.iocoder.yudao.module.smartcity.controller.admin.drainagesafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 排水安全新增/修改 Request VO")
@Data
public class DrainageSafetySaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "3160")
    private Long id;

    @Schema(description = "预警编号")
    private String warningNumber;

    @Schema(description = "预警时间")
    private String warningTime;

    @Schema(description = "预警级别")
    private String warningLevel;

    @Schema(description = "预警区域")
    private String warningArea;

    @Schema(description = "监测点位")
    private String monitoringPoints;

    @Schema(description = "实时水位")
    private String realTimeWaterLevel;

}
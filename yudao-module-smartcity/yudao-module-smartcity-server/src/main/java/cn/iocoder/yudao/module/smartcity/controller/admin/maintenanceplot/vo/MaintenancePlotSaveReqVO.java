package cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 养护地块新增/修改 Request VO")
@Data
public class MaintenancePlotSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15624")
    private Long id;

    @Schema(description = "地块名称", example = "芋艿")
    private String plotName;

    @Schema(description = "地块描述", example = "你猜")
    private String plotDescription;

    @Schema(description = "归属信息")
    private String ownership;

    @Schema(description = "地块面积")
    private String acreage;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "绿化类型", example = "2")
    private String greeningType;

    @Schema(description = "上次养护的日期")
    private LocalDateTime lastMaintenanceDate;

    @Schema(description = "下次养护的计划日期")
    private LocalDateTime nextMaintenanceDate;

}
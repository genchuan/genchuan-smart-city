package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 责任人预警等级统计 Response VO")
@Data
public class ResponsiblePersonLevelStatisticsRespVO {

    @Schema(description = "预警等级", example = "紧急")
    private String name;

    @Schema(description = "告警数量", example = "5")
    private Integer value;

}
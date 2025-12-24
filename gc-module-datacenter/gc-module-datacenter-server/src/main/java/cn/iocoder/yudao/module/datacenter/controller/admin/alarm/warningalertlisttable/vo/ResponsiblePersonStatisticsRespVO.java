package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 责任人告警统计 Response VO")
@Data
public class ResponsiblePersonStatisticsRespVO {

    @Schema(description = "责任人姓名", example = "张三")
    private String name;

    @Schema(description = "告警数量", example = "5")
    private Integer value;
}
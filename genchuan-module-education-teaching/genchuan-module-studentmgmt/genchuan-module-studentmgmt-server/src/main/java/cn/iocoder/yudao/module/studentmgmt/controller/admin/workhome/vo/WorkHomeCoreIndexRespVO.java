package cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 核心指标统计 Response VO")
@Data
public class WorkHomeCoreIndexRespVO {
    @Schema(description = "统计周期日期")
    private String date;
    @Schema(description = "本周 / 月新增荣誉数")
    private Integer honorCount;
    @Schema(description = "本周 / 月新增违纪数")
    private Integer violateCount;
    @Schema(description = "本周 / 月新增考评数")
    private Integer assessCount;
}
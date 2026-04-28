package cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Schema(description = "管理后台 - 生成周期报表 Request VO", example = "{\n  \"reportCycle\": \"自定义报表\",\n  \"statStartTime\": 1714521600000,\n  \"statEndTime\": 1716470399000,\n  \"stationId\": 1,\n  \"remark\": \"丰泽场站 4 月通行统计\"\n}")
@Data
public class CycleReportCreateReqVO {

    @Schema(description = "报表周期：日报/周报/月报/季报/半年报/年报/自定义报表", required = true, example = "自定义报表")
    @NotBlank(message = "报表周期不能为空")
    private String reportCycle;

    @Schema(description = "统计开始时间(时间戳，单位毫秒)", required = true, example = "1714521600000")
    @NotNull(message = "统计开始时间不能为空")
    private Long statStartTime;

    @Schema(description = "统计结束时间(时间戳单位毫秒)", required = true, example = "1716470399000")
    @NotNull(message = "统计结束时间不能为空")
    private Long statEndTime;

    @Schema(description = "场站ID", required = true, example = "1")
    @NotNull(message = "场站ID不能为空")
    private Long stationId;

    @Schema(description = "备注", example = "丰泽场站 4 月通行统计")
    private String remark;

    public LocalDateTime getStatStartTimeDate() {
        return statStartTime != null ? LocalDateTime.ofInstant(Instant.ofEpochMilli(statStartTime), ZoneId.systemDefault()) : null;
    }

    public LocalDateTime getStatEndTimeDate() {
        return statEndTime != null ? LocalDateTime.ofInstant(Instant.ofEpochMilli(statEndTime), ZoneId.systemDefault()) : null;
    }

}
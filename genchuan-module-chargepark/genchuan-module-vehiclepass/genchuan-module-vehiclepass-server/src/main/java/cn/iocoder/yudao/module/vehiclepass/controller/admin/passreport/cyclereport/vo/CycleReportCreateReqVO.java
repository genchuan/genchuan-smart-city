package cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 生成周期报表 Request VO", example = "{\n  \"reportCycle\": \"自定义报表\",\n  \"statStartTime\": \"2026-04-01 00:00:00\",\n  \"statEndTime\": \"2026-04-30 23:59:59\",\n  \"stationId\": 1,\n  \"remark\": \"丰泽场站 4 月通行统计\"\n}")
@Data
public class CycleReportCreateReqVO {

    @Schema(description = "报表周期：日报/周报/月报/季报/半年报/年报/自定义报表", required = true, example = "自定义报表")
    @NotBlank(message = "报表周期不能为空")
    private String reportCycle;

    @Schema(description = "统计开始时间", required = true, example = "2026-04-01 00:00:00")
    @NotNull(message = "统计开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statStartTime;

    @Schema(description = "统计结束时间", required = true, example = "2026-04-30 23:59:59")
    @NotNull(message = "统计结束时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statEndTime;

    @Schema(description = "场站ID", required = true, example = "1")
    @NotNull(message = "场站ID不能为空")
    private Long stationId;

    @Schema(description = "备注", example = "丰泽场站 4 月通行统计")
    private String remark;

}
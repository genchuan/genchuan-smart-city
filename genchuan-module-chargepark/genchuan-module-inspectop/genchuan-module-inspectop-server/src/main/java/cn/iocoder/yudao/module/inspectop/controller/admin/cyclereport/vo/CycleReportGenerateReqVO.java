// 文件: CycleReportGenerateReqVO.java
package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Schema(description = "巡查巡检 - 生成巡检运维报表 Request VO")
@Data
public class CycleReportGenerateReqVO {

    @Schema(description = "报表周期（日报 / 周报 / 月报 / 季报 / 半年报 / 年报 / 自定义报表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "自定义报表")
    @NotNull(message = "报表周期不能为空")
    private String reportCycle;

    @Schema(description = "所属场站 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "所属场站 ID 不能为空")
    private Long stationId;

    @Schema(description = "统计开始时间，格式 yyyy-MM-dd HH:mm:ss", example = "2026-04-01 00:00:00")
    private String statTimeStart;

    @Schema(description = "统计结束时间，格式 yyyy-MM-dd HH:mm:ss", example = "2026-04-22 23:59:59")
    private String statTimeEnd;

    @Schema(description = "报表类型（自定义 / 自动）", example = "自定义")
    private String reportType;

    /**
     * 获取开始时间的 LocalDateTime 对象
     */
    public LocalDateTime getStatTimeStartAsLocalDateTime() {
        if (this.statTimeStart == null || this.statTimeStart.trim().isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(this.statTimeStart, formatter);
    }

    /**
     * 获取结束时间的 LocalDateTime 对象
     */
    public LocalDateTime getStatTimeEndAsLocalDateTime() {
        if (this.statTimeEnd == null || this.statTimeEnd.trim().isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(this.statTimeEnd, formatter);
    }

}
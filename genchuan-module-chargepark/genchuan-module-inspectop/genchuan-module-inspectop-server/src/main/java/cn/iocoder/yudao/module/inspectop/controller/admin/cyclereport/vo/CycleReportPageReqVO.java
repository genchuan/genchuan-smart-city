package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "巡查巡检 - 巡检运维报表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CycleReportPageReqVO extends PageParam {

    @Schema(description = "报表周期（日报 / 周报 / 月报 / 季报 / 半年报 / 年报 / 自定义报表）", example = "月报")
    private String reportCycle;

    @Schema(description = "所属场站 ID", example = "1001")
    private Long stationId;

    @Schema(description = "统计开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statTimeStart;

    @Schema(description = "统计结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statTimeEnd;

    @Schema(description = "生成状态（未生成 / 已生成）", example = "已生成")
    private String generateStatus;

    @Schema(description = "租户 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long tenantId;
}
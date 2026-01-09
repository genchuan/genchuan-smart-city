package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.overview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 通用大屏全域数据地图 查询 Request VO")
@Data
public class UniversalOverviewQueryReqVO {

    @Schema(description = "行政区域shortCode，6位行政区划码，对应部件标识码前6位", example = "110000")
    private String regionShortCode;

    @Schema(description = "设施类型（管理部件小类ID）", example = "minor_xxx")
    private String minorId;

    @Schema(description = "设施状态（完好/破损/丢失/废弃）", example = "破损")
    private String compStatus;

    @Schema(description = "统计开始时间（含）,yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间（含）,yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}

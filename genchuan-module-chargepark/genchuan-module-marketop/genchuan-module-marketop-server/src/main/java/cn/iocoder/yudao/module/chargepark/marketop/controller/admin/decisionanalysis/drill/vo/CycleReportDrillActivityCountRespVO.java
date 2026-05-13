package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表钻取-活动数 Response VO")
@Data
public class CycleReportDrillActivityCountRespVO {

    @Schema(description = "活动ID")
    private Long id;
    @Schema(description = "活动名称")
    private String name;
    @Schema(description = "活动类型")
    private String type;
    @Schema(description = "活动开始时间")
    private LocalDateTime startTime;
    @Schema(description = "活动结束时间")
    private LocalDateTime endTime;
    @Schema(description = "参与人数")
    private Integer joinCount;
    @Schema(description = "活动状态")
    private String status;
    @Schema(description = "创建者")
    private String creator;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "租户ID")
    private Long tenantId;

}

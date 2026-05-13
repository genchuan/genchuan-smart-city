package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表图表卡片钻取-活动数 Response VO")
@Data
public class CycleReportChartCardDrillActivityCountRespVO {

    @Schema(description = "活动ID，关联积分活动表point_activity")
    private Long id;

    @Schema(description = "活动名称，关联point_activity.name")
    private String name;

    @Schema(description = "活动类型（注册赠分/消费赠分/邀请赠分/活动赠分，关联芋道字典表：point_activity_type）")
    private String type;

    @Schema(description = "活动开始时间，关联point_activity.start_time")
    private LocalDateTime startTime;

    @Schema(description = "活动结束时间，关联point_activity.end_time")
    private LocalDateTime endTime;

    @Schema(description = "参与人数，关联point_activity.join_count")
    private Integer joinCount;

    @Schema(description = "活动状态（待生效/进行中/已结束/已暂停，关联芋道字典表：point_activity_status）")
    private String status;

    @Schema(description = "创建者，关联point_activity.creator")
    private String creator;

    @Schema(description = "创建时间，关联point_activity.create_time")
    private LocalDateTime createTime;

    @Schema(description = "租户ID，关联point_activity.tenant_id")
    private Long tenantId;

}

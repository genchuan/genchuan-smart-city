package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表图表卡片钻取-参与用户数 Response VO")
@Data
public class CycleReportChartCardDrillJoinUserCountRespVO {

    @Schema(description = "用户ID，关联芋道用户表system_user")
    private Long userId;

    @Schema(description = "用户名，关联system_user.username")
    private String userName;

    @Schema(description = "所属部门，关联芋道部门表system_dept")
    private String deptName;

    @Schema(description = "参与活动时间")
    private LocalDateTime joinTime;

    @Schema(description = "参与活动ID，关联point_activity.id")
    private Long joinActivityId;

    @Schema(description = "参与活动名称，关联point_activity.name")
    private String joinActivityName;

    @Schema(description = "租户ID")
    private Long tenantId;

}

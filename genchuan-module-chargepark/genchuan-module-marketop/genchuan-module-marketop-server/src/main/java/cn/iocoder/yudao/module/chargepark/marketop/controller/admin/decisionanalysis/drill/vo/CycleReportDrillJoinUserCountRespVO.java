package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表钻取-参与用户数 Response VO")
@Data
public class CycleReportDrillJoinUserCountRespVO {

    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "用户名")
    private String userName;
    @Schema(description = "所属部门")
    private String deptName;
    @Schema(description = "参与活动时间")
    private LocalDateTime joinTime;
    @Schema(description = "参与活动ID")
    private Long joinActivityId;
    @Schema(description = "参与活动名称")
    private String joinActivityName;
    @Schema(description = "租户ID")
    private Long tenantId;

}

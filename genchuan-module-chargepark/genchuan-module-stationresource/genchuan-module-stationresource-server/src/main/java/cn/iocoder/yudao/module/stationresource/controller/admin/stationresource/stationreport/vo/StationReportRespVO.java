package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 场站资源报表 Response VO
 */
@Data
@Schema(description = "场站资源报表")
public class StationReportRespVO {

    @Schema(description = "报表记录ID")
    private Long id;

    @Schema(description = "报表周期")
    private String reportCycle;

    @Schema(description = "开始时间")
    private String startTime;

    @Schema(description = "结束时间")
    private String endTime;

    @Schema(description = "总片区数")
    private Integer totalAreaNum;

    @Schema(description = "覆盖场站数")
    private Integer coverStationNum;

    @Schema(description = "总站场数")
    private Integer totalStationNum;

    @Schema(description = "正常运营场站数")
    private Integer normalOperateStationNum;

    @Schema(description = "总车位数")
    private Integer totalSpaceNum;

    @Schema(description = "可用车位数")
    private Integer availableSpaceNum;

    @Schema(description = "生效规则数")
    private Integer effectiveRuleNum;

    @Schema(description = "订单量")
    private Integer orderNum;

    @Schema(description = "营收")
    private BigDecimal income;

    @Schema(description = "追缴完成率")
    private BigDecimal recoveryRate;

    @Schema(description = "押金订单量")
    private Integer depositOrderNum;

    @Schema(description = "生成状态")
    private String generateStatus;

    @Schema(description = "生成时间")
    private LocalDateTime generateTime;

    @Schema(description = "操作人")
    private String operator;

    @Schema(description = "导出次数")
    private Integer exportCount;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}

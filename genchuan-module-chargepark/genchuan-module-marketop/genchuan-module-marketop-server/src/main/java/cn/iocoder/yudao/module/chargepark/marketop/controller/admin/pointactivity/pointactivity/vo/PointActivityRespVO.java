package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 积分活动 Response VO")
@Data
public class PointActivityRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "活动名称")
    private String name;

    @Schema(description = "活动类型")
    private String type;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "积分规则")
    private String rule;

    @Schema(description = "活动描述")
    private String description;

    @Schema(description = "适用场站")
    private String stationIds;

    @Schema(description = "参与人数")
    private Integer joinCount;

    @Schema(description = "审核人")
    private Long auditorId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "剩余积分额度")
    private Integer remainPoint;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}

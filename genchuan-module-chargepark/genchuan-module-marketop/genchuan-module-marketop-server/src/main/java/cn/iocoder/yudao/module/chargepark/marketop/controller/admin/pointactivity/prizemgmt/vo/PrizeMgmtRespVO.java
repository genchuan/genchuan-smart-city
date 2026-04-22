package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 奖品管理 Response VO")
@Data
public class PrizeMgmtRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "奖品名称")
    private String name;

    @Schema(description = "奖品类型")
    private String type;

    @Schema(description = "当前库存")
    private Integer stock;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "绑定活动ID")
    private Long activityId;

    @Schema(description = "发放量")
    private Integer sendCount;

    @Schema(description = "同步时间")
    private LocalDateTime syncTime;

    @Schema(description = "预警阈值")
    private Integer warnThreshold;

    @Schema(description = "奖品描述")
    private String description;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建者名称")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}

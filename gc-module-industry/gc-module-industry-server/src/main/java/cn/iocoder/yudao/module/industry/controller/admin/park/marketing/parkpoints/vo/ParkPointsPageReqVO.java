package cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpoints.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户积分分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkPointsPageReqVO extends PageParam {

    @Schema(description = "[用户ID] 用户唯一标识", example = "29644")
    private Long userId;

    @Schema(description = "[当前总积分] 用户当前累计的总积分")
    private BigDecimal totalPoints;

    @Schema(description = "[可用积分] 当前可使用的积分")
    private BigDecimal availablePoints;

    @Schema(description = "[已使用积分] 已被消耗使用的积分")
    private BigDecimal usedPoints;

    @Schema(description = "[已过期积分] 已过期失效的积分")
    private BigDecimal expiredPoints;

    @Schema(description = "[上次更新时间] 积分数据上次变更时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastUpdateTime;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注] 用户积分相关备注说明", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}

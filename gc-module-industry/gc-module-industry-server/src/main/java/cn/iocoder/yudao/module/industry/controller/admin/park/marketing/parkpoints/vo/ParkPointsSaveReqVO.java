package cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpoints.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户积分新增/修改 Request VO")
@Data
public class ParkPointsSaveReqVO {

    @Schema(description = "[主键ID] 用户积分记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "16676")
    private Long id;

    @Schema(description = "[用户ID] 用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "29644")
    @NotNull(message = "[用户ID] 用户唯一标识不能为空")
    private Long userId;

    @Schema(description = "[当前总积分] 用户当前累计的总积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[当前总积分] 用户当前累计的总积分不能为空")
    private BigDecimal totalPoints;

    @Schema(description = "[可用积分] 当前可使用的积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[可用积分] 当前可使用的积分不能为空")
    private BigDecimal availablePoints;

    @Schema(description = "[已使用积分] 已被消耗使用的积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[已使用积分] 已被消耗使用的积分不能为空")
    private BigDecimal usedPoints;

    @Schema(description = "[已过期积分] 已过期失效的积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[已过期积分] 已过期失效的积分不能为空")
    private BigDecimal expiredPoints;

    @Schema(description = "[上次更新时间] 积分数据上次变更时间")
    private LocalDateTime lastUpdateTime;

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

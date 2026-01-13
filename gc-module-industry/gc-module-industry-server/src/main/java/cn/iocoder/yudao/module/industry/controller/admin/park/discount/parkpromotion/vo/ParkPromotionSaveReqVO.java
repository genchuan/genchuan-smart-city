package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkpromotion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 优惠活动新增/修改 Request VO")
@Data
public class ParkPromotionSaveReqVO {

    @Schema(description = "[主键ID] 优惠活动唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "7612")
    private Long id;

    @Schema(description = "[活动名称] 优惠活动名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "[活动名称] 优惠活动名称不能为空")
    private String activityName;

    @Schema(description = "[活动类型] 满减 / 折扣 / 赠送 / 充值送 / 其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[活动类型] 满减 / 折扣 / 赠送 / 充值送 / 其他不能为空")
    private String activityType;

    @Schema(description = "[适用范围] 全局 / 区域 / 车场 / 用户类型（JSON字符串）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[适用范围] 全局 / 区域 / 车场 / 用户类型（JSON字符串）不能为空")
    private String applyScope;

    @Schema(description = "[活动开始时间] 活动生效开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[活动开始时间] 活动生效开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "[活动结束时间] 活动生效结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[活动结束时间] 活动生效结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "[活动总名额] NULL 表示不限")
    private Integer quota;

    @Schema(description = "[已使用名额] 已消耗活动名额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[已使用名额] 已消耗活动名额不能为空")
    private Integer usedQuota;

    @Schema(description = "[状态] 未开始 / 进行中 / 已结束 / 已取消", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[状态] 未开始 / 进行中 / 已结束 / 已取消不能为空")
    private String status;

    @Schema(description = "[活动规则配置] 活动规则定义（JSON字符串）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[活动规则配置] 活动规则定义（JSON字符串）不能为空")
    private String ruleConfig;

    @Schema(description = "[活动数据统计] 统计信息（JSON字符串）")
    private String dataStatistics;

    @Schema(description = "[备注] 优惠活动相关说明", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1] 预留扩展")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留扩展")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留扩展")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留扩展")
    private String extCommon4;

}

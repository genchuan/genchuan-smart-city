package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkpromotion.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 优惠活动分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkPromotionPageReqVO extends PageParam {

    @Schema(description = "[活动名称] 优惠活动名称", example = "王五")
    private String activityName;

    @Schema(description = "[活动类型] 满减 / 折扣 / 赠送 / 充值送 / 其他", example = "1")
    private String activityType;

    @Schema(description = "[适用范围] 全局 / 区域 / 车场 / 用户类型（JSON字符串）")
    private String applyScope;

    @Schema(description = "[活动开始时间] 活动生效开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "[活动结束时间] 活动生效结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "[活动总名额] NULL 表示不限")
    private Integer quota;

    @Schema(description = "[已使用名额] 已消耗活动名额")
    private Integer usedQuota;

    @Schema(description = "[状态] 未开始 / 进行中 / 已结束 / 已取消", example = "2")
    private String status;

    @Schema(description = "[活动规则配置] 活动规则定义（JSON字符串）")
    private String ruleConfig;

    @Schema(description = "[活动数据统计] 统计信息（JSON字符串）")
    private String dataStatistics;

    @Schema(description = "[备注] 优惠活动相关说明", example = "随便")
    private String remark;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 预留扩展")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留扩展")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留扩展")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留扩展")
    private String extCommon4;

}

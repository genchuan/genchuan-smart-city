package cn.iocoder.yudao.module.park.controller.admin.park.pricing.promotion.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 优惠活动分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PromotionPageReqVO extends PageParam {

    @Schema(description = "[活动名称]", example = "赵六")
    private String activityName;

    @Schema(description = "[活动类型] 如:满减/折扣/充值送/免费时长", example = "1")
    private String activityType;

    @Schema(description = "[活动开始时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "[活动结束时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "[总名额] 可为NULL")
    private Integer quota;

    @Schema(description = "[已使用名额]")
    private Integer usedQuota;

    @Schema(description = "[适用范围] 如:全局/区域/车场")
    private String applyScope;

    @Schema(description = "[适用范围ID列表] JSON格式varchar")
    private String scopeIds;

    @Schema(description = "[活动规则] JSON格式varchar，如满减金额/折扣比例")
    private String ruleConfig;

    @Schema(description = "[状态] 如:未开始/进行中/已结束", example = "1")
    private String status;

    @Schema(description = "[创建人ID] 关联park_user.id")
    private Long createBy;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注]", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}

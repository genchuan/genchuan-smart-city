package cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 通行规则分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkPassRulePageReqVO extends PageParam {

    @Schema(description = "通行规则ID（UUID）", example = "24778")
    private String passRuleId;

    @Schema(description = "规则名称", example = "张三")
    private String ruleName;

    @Schema(description = "关联出入口ID", example = "15051")
    private String entryExitId;

    @Schema(description = "允许车辆类型")
    private String allowCarTypes;

    @Schema(description = "禁止车辆类型")
    private String forbidCarTypes;

    @Schema(description = "高峰时段规则")
    private String peakTimeRule;

    @Schema(description = "平峰时段规则")
    private String offPeakTimeRule;

    @Schema(description = "状态：启用/禁用", example = "2")
    private String status;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] passRuleCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] passRuleUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    private String passRuleRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
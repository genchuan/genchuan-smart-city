package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnarch.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警告警处置归档分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EarlyWarnArchPageReqVO extends PageParam {

    @Schema(description = "归档ID")
    private String archId;

    @Schema(description = "归档编号")
    private String archNo;

    @Schema(description = "预警ID")
    private String earlyWarnId;

    @Schema(description = "预警名称")
    private String earlyWarnName;

    @Schema(description = "预警类型")
    private String warnType;

    @Schema(description = "预警等级")
    private String warnLevel;

    @Schema(description = "所在区域")
    private String regionName;

    @Schema(description = "处置人")
    private String handleUserName;

    @Schema(description = "综合评估得分")
    private BigDecimal comprehensiveScore;

    @Schema(description = "解除状态")
    private String releaseStatus;

    @Schema(description = "归档时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] archTime;

    @Schema(description = "归档人ID")
    private String archUserId;

    @Schema(description = "归档人姓名")
    private String archUserName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
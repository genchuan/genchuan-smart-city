package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemlevelrec.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检问题分级记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectProblemLevelRecPageReqVO extends PageParam {

    @Schema(description = "分级ID")
    private String problemLevelId;

    @Schema(description = "关联问题ID")
    private String problemId;

    @Schema(description = "关联问题编码")
    private String problemCode;

    @Schema(description = "关联问题名称")
    private String problemName;

    @Schema(description = "问题级别")
    private String problemLevel;

    @Schema(description = "级别描述")
    private String levelDesc;

    @Schema(description = "处置时限")
    private Integer handleDeadline;

    @Schema(description = "分级依据")
    private String levelBasis;

    @Schema(description = "分级人")
    private String levelUser;

    @Schema(description = "分级时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] levelTime;

    @Schema(description = "是否推送提醒")
    private Boolean isPushRemind;

    @Schema(description = "提醒推送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] pushRemindTime;

    @Schema(description = "问题状态变更")
    private String problemStatusChange;

    @Schema(description = "创建人")
    private String bizCreateUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "更新人")
    private String bizUpdateUser;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizUpdateTime;

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
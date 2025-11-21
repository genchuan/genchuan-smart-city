package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检整改任务派发分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectRectifyTaskPageReqVO extends PageParam {

    @Schema(description = "整改任务ID")
    private String rectifyTaskId;

    @Schema(description = "整改任务编码")
    private String rectifyTaskCode;

    @Schema(description = "关联问题ID")
    private String problemId;

    @Schema(description = "关联问题编码")
    private String problemCode;

    @Schema(description = "关联问题名称")
    private String problemName;

    @Schema(description = "责任部门代码")
    private String deptCode;

    @Schema(description = "责任部门名称")
    private String deptName;

    @Schema(description = "整改负责人ID")
    private String leaderUserId;

    @Schema(description = "整改负责人姓名")
    private String leaderUserName;

    @Schema(description = "整改时限（小时）")
    private Integer rectifyDeadlineHour;

    @Schema(description = "整改截止时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rectifyDeadlineTime;

    @Schema(description = "派发说明")
    private String dispatchDesc;

    @Schema(description = "整改任务状态")
    private String rectifyStatus;

    @Schema(description = "派发人")
    private String dispatchUser;

    @Schema(description = "派发时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] dispatchTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

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
package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检整改进度跟踪视图分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectRectifyTrackPageReqVO extends PageParam {

    @Schema(description = "整改任务ID")
    private String rectifyTaskId;

    @Schema(description = "整改任务编码")
    private String rectifyTaskCode;

    @Schema(description = "关联问题名称")
    private String problemName;

    @Schema(description = "责任部门名称")
    private String deptName;

    @Schema(description = "整改负责人姓名")
    private String leaderUserName;

    @Schema(description = "整改任务状态")
    private String rectifyStatus;

    @Schema(description = "整改时限（小时）")
    private Integer rectifyDeadlineHour;

    @Schema(description = "整改截止时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rectifyDeadlineTime;

    @Schema(description = "剩余时间（分钟）")
    private Integer remainTimeMin;

    @Schema(description = "是否超期")
    private Boolean isOverdue;

    @Schema(description = "最新进展时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] latestProgressTime;

    @Schema(description = "最新进展描述")
    private String latestProgressDesc;

    @Schema(description = "最新进展照片URL")
    private String latestProgressPhoto;

    @Schema(description = "催办次数")
    private Integer remindCount;

    @Schema(description = "最后催办时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastRemindTime;

    @Schema(description = "派发时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] dispatchTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
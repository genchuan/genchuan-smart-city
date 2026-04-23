package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 稽查任务分页 Request VO")
@Data
public class CheckTaskPageReqVO extends PageParam {

    @Schema(description = "任务类型：违规通行稽查/欠费逃费稽查/其他")
    private String taskType;

    @Schema(description = "派发时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] dispatchTime;

    @Schema(description = "截止时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] deadlineTime;

    @Schema(description = "状态：待派发/待认领/处理中/已完成/已归档")
    private String status;

    @Schema(description = "片区ID")
    private Long areaId;

    @Schema(description = "执行人ID")
    private Long executeUserId;

    @Schema(description = "完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] finishTime;

    @Schema(description = "任务进度")
    private String taskProgress;

    @Schema(description = "转派理由")
    private String transferReason;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    // ========== 新增字段（用于/my/page筛选刷新）==========

    @Schema(description = "派发时间，时间戳格式")
    private String[] dispatchTimeNew;

    @Schema(description = "截止时间，时间戳格式")
    private String[] deadlineTimeNew;

    @Schema(description = "完成时间，时间戳格式")
    private String[] finishTimeNew;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}
package cn.iocoder.yudao.module.waterdetection.controller.admin.taskdispatch.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 任务派发分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskDispatchPageReqVO extends PageParam {

    @Schema(description = "任务编号")
    private String taskCode;

    @Schema(description = "任务类型(常规/应急)")
    private String taskType;

    @Schema(description = "检测点清单")
    private String testPoints;

    @Schema(description = "指标清单")
    private String indicators;

    @Schema(description = "截止日期")
    private LocalDateTime deadline;

    @Schema(description = "派发部门")
    private String dispatchDept;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
package cn.iocoder.yudao.module.waterdetection.controller.admin.inspectiontask.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡检任务派发与执行分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectionTaskPageReqVO extends PageParam {

    @Schema(description = "任务ID")
    private String taskId;

    @Schema(description = "巡检人员ID")
    private String inspectorId;

    @Schema(description = "任务内容")
    private String taskContent;

    @Schema(description = "派发时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] dispatchTime;

    @Schema(description = "接收时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] receiveTime;

    @Schema(description = "签到时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] checkinTime;

    @Schema(description = "检查项结果(正常/异常)")
    private String inspectionResult;

    @Schema(description = "现场照片URL")
    private String photoUrl;

    @Schema(description = "定位信息")
    private String locationInfo;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
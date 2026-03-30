package cn.iocoder.yudao.module.waterdetection.controller.admin.testprogress.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 检测进度跟踪分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestProgressPageReqVO extends PageParam {

    @Schema(description = "任务编号")
    private String taskCode;

    @Schema(description = "当前进度(%)")
    private Double progressPercent;

    @Schema(description = "已完成指标")
    private String completedIndicators;

    @Schema(description = "未完成指标")
    private String pendingIndicators;

    @Schema(description = "预计完成时间")
    private LocalDateTime estimatedCompletion;

    @Schema(description = "延迟原因(如有)")
    private String delayReason;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
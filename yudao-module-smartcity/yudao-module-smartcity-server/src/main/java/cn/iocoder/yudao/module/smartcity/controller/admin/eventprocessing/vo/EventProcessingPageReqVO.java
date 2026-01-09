package cn.iocoder.yudao.module.smartcity.controller.admin.eventprocessing.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 事件处理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EventProcessingPageReqVO extends PageParam {

    @Schema(description = "事件处理编号")
    private String eventHandlingNumber;

    @Schema(description = "关联事件上报编号", example = "31517")
    private String relatedEventReportingId;

    @Schema(description = "处理部门")
    private String processingDepartment;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
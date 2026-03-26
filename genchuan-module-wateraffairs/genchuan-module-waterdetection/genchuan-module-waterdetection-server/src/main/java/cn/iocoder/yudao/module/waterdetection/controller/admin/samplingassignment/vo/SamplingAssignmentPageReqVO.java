package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingassignment.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 采样人员分配分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SamplingAssignmentPageReqVO extends PageParam {

    @Schema(description = "采样计划编号")
    private String planCode;

    @Schema(description = "采样点清单")
    private String pointList;

    @Schema(description = "负责人员")
    private String responsiblePerson;

    @Schema(description = "分配时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] assignTime;

    @Schema(description = "完成时限")
    private LocalDateTime deadline;

    @Schema(description = "联系方式")
    private String contactInfo;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
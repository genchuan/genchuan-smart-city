package cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplan.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 养护计划分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MaintenancePlanPageReqVO extends PageParam {

    @Schema(description = "计划编号")
    private String planNumber;

    @Schema(description = "计划名称", example = "王五")
    private String planName;

    @Schema(description = "养护地块")
    private String maintainParcel;

    @Schema(description = "养护内容")
    private String maintenanceContent;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
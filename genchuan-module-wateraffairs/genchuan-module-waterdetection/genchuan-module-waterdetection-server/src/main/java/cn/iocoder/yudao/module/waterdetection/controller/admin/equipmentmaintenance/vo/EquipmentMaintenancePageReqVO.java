package cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentmaintenance.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备保养计划管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EquipmentMaintenancePageReqVO extends PageParam {

    @Schema(description = "设备ID")
    private String equipmentId;

    @Schema(description = "设备类型")
    private String equipmentType;

    @Schema(description = "保养周期(天)")
    private Double maintenanceCycle;

    @Schema(description = "计划保养日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] planMaintenanceDate;

    @Schema(description = "实际保养日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] actualMaintenanceDate;

    @Schema(description = "保养内容")
    private String maintenanceContent;

    @Schema(description = "更换部件名称")
    private String replacedParts;

    @Schema(description = "保养后运行参数")
    private String postMaintenanceParams;

    @Schema(description = "维护人员ID")
    private String maintenanceStaffId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
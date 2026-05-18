package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 巡检任务分页 Request VO")
@Data
public class InspectTaskPageReqVO extends PageParam {

    @Schema(description = "关联计划ID")
    private Long planId;

    @Schema(description = "所属计划")
    @ExcelProperty("所属计划")
    private String planName;

    @Schema(description = "任务类型")
    @ExcelProperty("任务类型")
    private String planTypeName;

    @Schema(description = "巡检人员ID")
    private Long userId;

    @Schema(description = "派发时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] dispatchTime;

    @Schema(description = "认领时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] claimTime;

    @Schema(description = "任务状态")
    private String status;

    @Schema(description = "执行进度")
    private Integer progress;

    @Schema(description = "是否归档")
    private Boolean isArchive;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

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

    @Schema(description = "更新时间（按月份筛选，格式：MM，例如 04 代表4月）")
    private String updateMonth; // 新增字段

}
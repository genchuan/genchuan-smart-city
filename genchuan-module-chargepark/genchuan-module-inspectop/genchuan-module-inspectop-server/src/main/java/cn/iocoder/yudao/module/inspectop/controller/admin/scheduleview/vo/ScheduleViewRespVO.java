package cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 排班查看 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ScheduleViewRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "巡检人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("巡检人员ID")
    private Long userId;

    @Schema(description = "巡检人员")
    @ExcelProperty("巡检人员")
    private String userName;

    @Schema(description = "排班日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("排班日期")
    private LocalDateTime scheduleDate;

    @Schema(description = "班次类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("班次类型")
    private String shiftType;

    @Schema(description = "排班状态")
    @ExcelProperty("排班状态")
    private String status;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
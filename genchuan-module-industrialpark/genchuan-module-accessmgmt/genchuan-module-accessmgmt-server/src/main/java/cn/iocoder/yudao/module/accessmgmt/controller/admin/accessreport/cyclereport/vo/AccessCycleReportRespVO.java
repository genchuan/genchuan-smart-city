package cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import cn.idev.excel.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 通行周期报表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AccessCycleReportRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "报表名称")
    @ExcelProperty("报表名称")
    private String reportName;

    @Schema(description = "周期类型（日报/周报/月报/季报/半年报/年报/自定义）")
    @ExcelProperty("周期类型")
    private String cycleType;

    @Schema(description = "开始时间")
    @ExcelProperty("开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    @ExcelProperty("结束时间")
    private LocalDateTime endTime;

    @Schema(description = "人员通行总量")
    @ExcelProperty("人员通行总量")
    private Integer totalPersonAccess;

    @Schema(description = "访客到访总量")
    @ExcelProperty("访客到访总量")
    private Integer totalVisitorArrive;

    @Schema(description = "车辆通行总量")
    @ExcelProperty("车辆通行总量")
    private Integer totalVehicleAccess;

    @Schema(description = "车位使用率")
    @ExcelProperty("车位使用率")
    private BigDecimal spaceUseRate;

    @Schema(description = "缴费收入")
    @ExcelProperty("缴费收入")
    private BigDecimal payIncome;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}

package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmonevt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 按监测事件预警告警统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EarlyWarnMonEvtRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计ID")
    private String warnMonEvtStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "事件大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件大类ID")
    private String evtMajorId;

    @Schema(description = "事件大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件大类名称")
    private String evtMajorName;

    @Schema(description = "事件小类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件小类ID")
    private String evtMinorId;

    @Schema(description = "事件小类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件小类名称")
    private String evtMinorName;

    @Schema(description = "预警总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警总数")
    private Integer totalWarnCount;

    @Schema(description = "平均响应时间")
    @ExcelProperty("平均响应时间")
    private BigDecimal avgRespTime;

    @Schema(description = "涉及区域数")
    @ExcelProperty("涉及区域数")
    private Integer regionCount;

    @Schema(description = "已办结数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("已办结数")
    private Integer completedCount;

    @Schema(description = "处置率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置率")
    private BigDecimal handleRate;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计人")
    private String statUserId;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间")
    private LocalDateTime statTime;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
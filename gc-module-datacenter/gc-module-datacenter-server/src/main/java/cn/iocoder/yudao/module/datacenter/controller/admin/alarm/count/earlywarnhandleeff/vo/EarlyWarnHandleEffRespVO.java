package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnhandleeff.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 预警告警处置效率统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EarlyWarnHandleEffRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "统计ID")
    @ExcelProperty("统计ID")
    private String efficacyStatId;

    @Schema(description = "统计周期")
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "处置部门ID")
    @ExcelProperty("处置部门ID")
    private String handleDeptId;

    @Schema(description = "处置部门名称")
    @ExcelProperty("处置部门名称")
    private String handleDeptName;

    @Schema(description = "处置预警总数")
    @ExcelProperty("处置预警总数")
    private Integer totalHandleCount;

    @Schema(description = "已办结数")
    @ExcelProperty("已办结数")
    private Integer completedCount;

    @Schema(description = "处置率")
    @ExcelProperty("处置率")
    private BigDecimal handleRate;

    @Schema(description = "平均处置时长")
    @ExcelProperty("平均处置时长")
    private BigDecimal avgHandleEndure;

    @Schema(description = "严重等级平均时长")
    @ExcelProperty("严重等级平均时长")
    private BigDecimal criticalAvgEndure;

    @Schema(description = "主要处置类型")
    @ExcelProperty("主要处置类型")
    private String mainHandleType;

    @Schema(description = "主要处置类型占比")
    @ExcelProperty("主要处置类型占比")
    private BigDecimal mainTypeProportion;

    @Schema(description = "统计人")
    @ExcelProperty("统计人")
    private String statUserId;

    @Schema(description = "统计时间")
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
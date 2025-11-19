package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectefficacyrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检效率统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectEfficacyRptRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计ID")
    private String efficacyStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期名称")
    private String statCycleName;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "参与巡查人员数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("参与巡查人员数")
    private Integer participantCount;

    @Schema(description = "总巡查次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("总巡查次数")
    private Integer totalInspectCount;

    @Schema(description = "人均巡查次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("人均巡查次数")
    private BigDecimal perPersonInspectCount;

    @Schema(description = "平均单次巡查时长", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("平均单次巡查时长")
    private Integer avgSingleEndure;

    @Schema(description = "上报问题总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上报问题总数")
    private Integer totalProblemCount;

    @Schema(description = "问题平均整改时长", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题平均整改时长")
    private Integer avgRectifyEndure;

    @Schema(description = "效率评估", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("效率评估")
    private String efficacyEval;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计人")
    private String statUser;

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
package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionresultsa.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查结果 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionResultsARespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "17992")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "编号")
    @ExcelProperty("编号")
    private String number;

    @Schema(description = "巡查人员")
    @ExcelProperty("巡查人员")
    private String patrolPersonnel;

    @Schema(description = "巡查时间")
    @ExcelProperty("巡查时间")
    private LocalDateTime patrolTime;

    @Schema(description = "巡查地点")
    @ExcelProperty("巡查地点")
    private String patrolLocation;

    @Schema(description = "资源名称", example = "赵六")
    @ExcelProperty("资源名称")
    private String resourceName;

    @Schema(description = "资源编号")
    @ExcelProperty("资源编号")
    private String resourceNumber;

    @Schema(description = "检查项目")
    @ExcelProperty("检查项目")
    private String inspectionItems;

    @Schema(description = "检查结果")
    @ExcelProperty("检查结果")
    private String inspectionResults;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
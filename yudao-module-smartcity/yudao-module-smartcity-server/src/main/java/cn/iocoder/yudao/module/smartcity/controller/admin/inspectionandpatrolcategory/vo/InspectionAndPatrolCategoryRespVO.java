package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionandpatrolcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionAndPatrolCategoryRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "32471")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "编号")
    @ExcelProperty("编号")
    private String number;

    @Schema(description = "巡查巡检时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("巡查巡检时间")
    private LocalDateTime inspectionAndPatrolTime;

    @Schema(description = "巡查巡检人员")
    @ExcelProperty("巡查巡检人员")
    private String patrolPersonnel;

    @Schema(description = "巡查巡检区域")
    @ExcelProperty("巡查巡检区域")
    private String patrolInspectionArea;

    @Schema(description = "巡查巡检内容")
    @ExcelProperty("巡查巡检内容")
    private String inspectionAndPatrolContent;

    @Schema(description = "异常情况描述", example = "你说的对")
    @ExcelProperty("异常情况描述")
    private String abnormalSituationDescription;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String notes;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
package cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 报警处置类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AlarmHandlingCategoryRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24176")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "报警时间")
    @ExcelProperty("报警时间")
    private LocalDateTime time;

    @Schema(description = "报警来源")
    @ExcelProperty("报警来源")
    private String alarmSource;

    @Schema(description = "风险等级")
    @ExcelProperty("风险等级")
    private String riskLevel;

    @Schema(description = "报警描述", example = "你说的对")
    @ExcelProperty("报警描述")
    private String alarmDescription;

    @Schema(description = "涉及区域")
    @ExcelProperty("涉及区域")
    private String involvingRegions;

    @Schema(description = "处置措施")
    @ExcelProperty("处置措施")
    private String disposalMeasures;

    @Schema(description = "处置结果")
    @ExcelProperty("处置结果")
    private String disposalResults;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String notes;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
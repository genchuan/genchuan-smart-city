package cn.iocoder.yudao.module.system.controller.admin.alarmhandlingcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 警报处理类别 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AlarmHandlingCategoryRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31127")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createBy;

    @Schema(description = "创建日期")
    @ExcelProperty("创建日期")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateBy;

    @Schema(description = "所属部门")
    @ExcelProperty("所属部门")
    private String sysOrgCode;

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

}
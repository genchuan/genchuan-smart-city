package cn.iocoder.yudao.module.smartcity.controller.admin.caseinvestigation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 案件调查 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CaseInvestigationRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23812")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "案件ID", example = "29928")
    @ExcelProperty("案件ID")
    private String caseId;

    @Schema(description = "调查负责人")
    @ExcelProperty("调查负责人")
    private String investigationLeader;

    @Schema(description = "调查组成员")
    @ExcelProperty("调查组成员")
    private String investigationTeam;

    @Schema(description = "调查开始时间")
    @ExcelProperty("调查开始时间")
    private LocalDateTime investigationStartTime;

    @Schema(description = "调查结束时间")
    @ExcelProperty("调查结束时间")
    private LocalDateTime investigationEndTime;

    @Schema(description = "调查情况描述")
    @ExcelProperty("调查情况描述")
    private String investigationDesc;

    @Schema(description = "证据情况描述")
    @ExcelProperty("证据情况描述")
    private String evidenceDesc;

    @Schema(description = "证人证言描述")
    @ExcelProperty("证人证言描述")
    private String testimonyDesc;

    @Schema(description = "调查结果")
    @ExcelProperty("调查结果")
    private String investigationResult;

    @Schema(description = "处理建议")
    @ExcelProperty("处理建议")
    private String treatmentSuggestion;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
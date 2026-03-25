package cn.iocoder.yudao.module.smartcity.controller.admin.casedisposal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 案件处理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CaseDisposalRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26740")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "案件ID", example = "14668")
    @ExcelProperty("案件ID")
    private String caseId;

    @Schema(description = "处理类型", example = "1")
    @ExcelProperty("处理类型")
    private String disposalType;

    @Schema(description = "处理部门")
    @ExcelProperty("处理部门")
    private String disposalDepartment;

    @Schema(description = "处理人")
    @ExcelProperty("处理人")
    private String disposalPerson;

    @Schema(description = "处理开始时间")
    @ExcelProperty("处理开始时间")
    private LocalDateTime disposalStartTime;

    @Schema(description = "处理结束时间")
    @ExcelProperty("处理结束时间")
    private LocalDateTime disposalEndTime;

    @Schema(description = "处理依据")
    @ExcelProperty("处理依据")
    private String disposalBasis;

    @Schema(description = "处理内容")
    @ExcelProperty("处理内容")
    private String disposalContent;

    @Schema(description = "处理结果")
    @ExcelProperty("处理结果")
    private String disposalResult;

    @Schema(description = "处罚金额")
    @ExcelProperty("处罚金额")
    private BigDecimal penaltyAmount;

    @Schema(description = "处罚类型", example = "1")
    @ExcelProperty("处罚类型")
    private String penaltyType;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
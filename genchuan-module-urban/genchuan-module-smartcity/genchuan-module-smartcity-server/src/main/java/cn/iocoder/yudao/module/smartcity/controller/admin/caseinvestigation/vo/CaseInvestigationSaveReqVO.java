package cn.iocoder.yudao.module.smartcity.controller.admin.caseinvestigation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 案件调查新增/修改 Request VO")
@Data
public class CaseInvestigationSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23812")
    private Long id;

    @Schema(description = "案件ID", example = "29928")
    private String caseId;

    @Schema(description = "调查负责人")
    private String investigationLeader;

    @Schema(description = "调查组成员")
    private String investigationTeam;

    @Schema(description = "调查开始时间")
    private LocalDateTime investigationStartTime;

    @Schema(description = "调查结束时间")
    private LocalDateTime investigationEndTime;

    @Schema(description = "调查情况描述")
    private String investigationDesc;

    @Schema(description = "证据情况描述")
    private String evidenceDesc;

    @Schema(description = "证人证言描述")
    private String testimonyDesc;

    @Schema(description = "调查结果")
    private String investigationResult;

    @Schema(description = "处理建议")
    private String treatmentSuggestion;

}
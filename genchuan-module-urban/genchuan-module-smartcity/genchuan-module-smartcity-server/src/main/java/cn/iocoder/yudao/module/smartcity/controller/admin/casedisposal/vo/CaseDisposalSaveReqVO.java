package cn.iocoder.yudao.module.smartcity.controller.admin.casedisposal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 案件处理新增/修改 Request VO")
@Data
public class CaseDisposalSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26740")
    private Long id;

    @Schema(description = "案件ID", example = "14668")
    private String caseId;

    @Schema(description = "处理类型", example = "1")
    private String disposalType;

    @Schema(description = "处理部门")
    private String disposalDepartment;

    @Schema(description = "处理人")
    private String disposalPerson;

    @Schema(description = "处理开始时间")
    private LocalDateTime disposalStartTime;

    @Schema(description = "处理结束时间")
    private LocalDateTime disposalEndTime;

    @Schema(description = "处理依据")
    private String disposalBasis;

    @Schema(description = "处理内容")
    private String disposalContent;

    @Schema(description = "处理结果")
    private String disposalResult;

    @Schema(description = "处罚金额")
    private BigDecimal penaltyAmount;

    @Schema(description = "处罚类型", example = "1")
    private String penaltyType;

}
package cn.iocoder.yudao.module.smartcity.controller.admin.caseacceptance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 案件受理新增/修改 Request VO")
@Data
public class CaseAcceptanceSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8073")
    private Long id;

    @Schema(description = "案件编号")
    private String caseCode;

    @Schema(description = "案件名称", example = "李四")
    private String caseName;

    @Schema(description = "案件类型", example = "1")
    private String caseType;

    @Schema(description = "案件来源")
    private String caseSource;

    @Schema(description = "案件时间")
    private LocalDateTime caseTime;

    @Schema(description = "案件地点")
    private String caseLocation;

    @Schema(description = "报案单位")
    private String reportUnit;

    @Schema(description = "报案人")
    private String reportPerson;

    @Schema(description = "联系电话")
    private String reportPhone;

    @Schema(description = "案件描述")
    private String caseDesc;

    @Schema(description = "案件状态", example = "1")
    private String caseStatus;

}
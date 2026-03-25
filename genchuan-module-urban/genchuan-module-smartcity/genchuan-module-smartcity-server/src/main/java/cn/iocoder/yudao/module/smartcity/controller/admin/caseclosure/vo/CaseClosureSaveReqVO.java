package cn.iocoder.yudao.module.smartcity.controller.admin.caseclosure.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 案件结案新增/修改 Request VO")
@Data
public class CaseClosureSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26110")
    private Long id;

    @Schema(description = "案件ID", example = "22843")
    private String caseId;

    @Schema(description = "结案原因", example = "不香")
    private String closureReason;

    @Schema(description = "结案部门")
    private String closureDepartment;

    @Schema(description = "结案人")
    private String closurePerson;

    @Schema(description = "结案时间")
    private LocalDateTime closureTime;

    @Schema(description = "审批人")
    private String approvalPerson;

    @Schema(description = "审批时间")
    private LocalDateTime approvalTime;

    @Schema(description = "审批意见")
    private String approvalOpinion;

    @Schema(description = "归档编号")
    private String archiveNumber;

    @Schema(description = "归档位置")
    private String archiveLocation;

}
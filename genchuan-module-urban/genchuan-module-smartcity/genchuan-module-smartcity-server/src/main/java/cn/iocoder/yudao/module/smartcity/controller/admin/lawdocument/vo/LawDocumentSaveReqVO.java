package cn.iocoder.yudao.module.smartcity.controller.admin.lawdocument.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 执法文书新增/修改 Request VO")
@Data
public class LawDocumentSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1716")
    private Long id;

    @Schema(description = "案件ID", example = "9444")
    private String caseId;

    @Schema(description = "文书类型", example = "1")
    private String documentType;

    @Schema(description = "文书编号")
    private String documentCode;

    @Schema(description = "文书标题")
    private String documentTitle;

    @Schema(description = "文书内容")
    private String documentContent;

    @Schema(description = "创建人")
    private String documentCreator;

    @Schema(description = "审批人")
    private String approver;

    @Schema(description = "审批时间")
    private LocalDateTime approvalTime;

    @Schema(description = "审批状态", example = "2")
    private String approvalStatus;

    @Schema(description = "签署人")
    private String signatory;

    @Schema(description = "签署时间")
    private LocalDateTime signTime;

    @Schema(description = "盖章状态", example = "1")
    private String sealStatus;

    @Schema(description = "盖章时间")
    private LocalDateTime sealTime;

    @Schema(description = "打印状态", example = "1")
    private String printStatus;

    @Schema(description = "打印次数")
    private Integer printTimes;

}
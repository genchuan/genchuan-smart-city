package cn.iocoder.yudao.module.smartcity.controller.admin.lawdocument.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 执法文书 Response VO")
@Data
@ExcelIgnoreUnannotated
public class LawDocumentRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1716")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "案件ID", example = "9444")
    @ExcelProperty("案件ID")
    private String caseId;

    @Schema(description = "文书类型", example = "1")
    @ExcelProperty("文书类型")
    private String documentType;

    @Schema(description = "文书编号")
    @ExcelProperty("文书编号")
    private String documentCode;

    @Schema(description = "文书标题")
    @ExcelProperty("文书标题")
    private String documentTitle;

    @Schema(description = "文书内容")
    @ExcelProperty("文书内容")
    private String documentContent;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String documentCreator;

    @Schema(description = "审批人")
    @ExcelProperty("审批人")
    private String approver;

    @Schema(description = "审批时间")
    @ExcelProperty("审批时间")
    private LocalDateTime approvalTime;

    @Schema(description = "审批状态", example = "2")
    @ExcelProperty("审批状态")
    private String approvalStatus;

    @Schema(description = "签署人")
    @ExcelProperty("签署人")
    private String signatory;

    @Schema(description = "签署时间")
    @ExcelProperty("签署时间")
    private LocalDateTime signTime;

    @Schema(description = "盖章状态", example = "1")
    @ExcelProperty("盖章状态")
    private String sealStatus;

    @Schema(description = "盖章时间")
    @ExcelProperty("盖章时间")
    private LocalDateTime sealTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
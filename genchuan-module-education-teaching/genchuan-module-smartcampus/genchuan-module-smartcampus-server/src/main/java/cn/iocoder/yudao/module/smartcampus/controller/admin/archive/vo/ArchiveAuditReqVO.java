package cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Schema(description = "智慧校园管理后台 - 学籍档案管理审核 Request VO")
@Data
public class ArchiveAuditReqVO {
    @Schema(description = "档案 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024,1025")
    private List<Long> ids;
    @Schema(description = "审核结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String processStatus;
    @Schema(description = "驳回原因")
    private String rejectReason;
}
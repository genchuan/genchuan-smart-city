package cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 稽查证据新增/修改 Request VO")
@Data
public class ParkInspectionEvidenceSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31550")
    private Long id;

    @Schema(description = "证据ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "27107")
    @NotEmpty(message = "证据ID（UUID）不能为空")
    private String evidenceId;

    @Schema(description = "稽查记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27633")
    @NotEmpty(message = "稽查记录ID不能为空")
    private String inspectionId;

    @Schema(description = "证据类型：图片/视频/日志", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "证据类型：图片/视频/日志不能为空")
    private String evidenceType;

    @Schema(description = "存储地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @NotEmpty(message = "存储地址不能为空")
    private String evidenceUrl;

    @Schema(description = "描述")
    private String evidenceDesc;

    @Schema(description = "上传时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上传时间不能为空")
    private LocalDateTime uploadTime;

    @Schema(description = "上传人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上传人ID不能为空")
    private Long uploadBy;

    @Schema(description = "业务创建时间")
    private LocalDateTime evidenceCreateTime;

    @Schema(description = "业务备注", example = "随便")
    private String evidenceRemark;

}
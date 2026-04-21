package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import cn.iocoder.yudao.framework.dict.validation.InDict;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 纠纷调解新增/修改 Request VO")
@Data
public class DisputeMediateSaveReqVO {

    @Schema(description = "主键 ID", example = "1024")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "用户 ID 不能为空")
    private Long userId;

    @Schema(description = "商户 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5001")
    @NotNull(message = "商户 ID 不能为空")
    private Long merchantId;

    @Schema(description = "纠纷内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "纠纷内容不能为空")
    private String content;

    @Schema(description = "发起时间（创建时由后端默认为当前时间）")
    private LocalDateTime submitTime;

    @Schema(description = "调解状态,关联字典 dispute_mediate_status(创建时由后端默认为 待调解)",
            example = "待调解",
            allowableValues = {"待调解", "调解中", "已完成"})
    @InDict(type = "dispute_mediate_status")
    private String status;

    @Schema(description = "调解人 ID")
    private Long mediateUserId;

    @Schema(description = "调解进度")
    private String progress;

    @Schema(description = "确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}

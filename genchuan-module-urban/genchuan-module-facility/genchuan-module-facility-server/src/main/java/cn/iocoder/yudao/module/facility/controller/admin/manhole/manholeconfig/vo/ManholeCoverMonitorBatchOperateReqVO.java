package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "井盖监测 批量启动/停止 通用请求参数")
public class ManholeCoverMonitorBatchOperateReqVO {

    @NotEmpty(message = "井盖ID列表不能为空")
    @Schema(description = "井盖ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> coverIds;

    @NotNull(message = "操作类型不能为空")
    @Schema(description = "操作类型 1-启动监测 0-停止监测", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer operateType;

    @NotBlank(message = "租户ID不能为空")
    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tenantId;

    @NotBlank(message = "操作人ID不能为空")
    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String operateUserId;
}
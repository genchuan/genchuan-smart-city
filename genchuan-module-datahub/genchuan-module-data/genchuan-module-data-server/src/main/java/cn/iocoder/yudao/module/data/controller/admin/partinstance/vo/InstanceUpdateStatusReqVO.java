package cn.iocoder.yudao.module.data.controller.admin.partinstance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 批量更新部件实例状态 Request VO")
@Data
public class InstanceUpdateStatusReqVO {

    @Schema(description = "实例ID列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1, 2, 3]")
    @NotEmpty(message = "实例ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "目标运行状态ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "目标运行状态不能为空")
    private String runStatus;
}
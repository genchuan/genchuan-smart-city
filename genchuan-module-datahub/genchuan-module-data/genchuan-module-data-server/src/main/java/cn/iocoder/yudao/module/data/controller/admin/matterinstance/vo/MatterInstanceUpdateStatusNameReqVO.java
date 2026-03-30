package cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 批量更新事项实例状态名称 Request VO")
@Data
public class MatterInstanceUpdateStatusNameReqVO {

    @Schema(description = "实例ID列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1, 2, 3]")
    @NotEmpty(message = "实例ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "目标状态名称", requiredMode = Schema.RequiredMode.REQUIRED, example ="1")
    @NotNull(message = "目标状态名称不能为空")
    private String status;
}
package cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Schema(description = "管理后台 - 监测事件实例批量更新状态 Request VO")
@Data
@ToString(callSuper = true)
public class EventInstanceUpdateStatusReqVO {

    @Schema(description = "实例ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "实例ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private String status;
}
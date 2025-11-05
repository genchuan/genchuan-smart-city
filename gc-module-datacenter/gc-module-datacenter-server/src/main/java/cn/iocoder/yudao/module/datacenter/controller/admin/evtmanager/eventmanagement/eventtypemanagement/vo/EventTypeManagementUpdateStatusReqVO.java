package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 事件类型管理更新状态 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeManagementUpdateStatusReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "启用状态，启用/禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "启用")
    @NotBlank(message = "启用状态不能为空")
    private String enableStatus;
}
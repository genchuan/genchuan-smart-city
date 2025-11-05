package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 事件类型管理创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class EventTypeManagementCreateReqVO extends EventTypeManagementBaseVO {

    @Schema(description = "类型层级：big-大类, mid-中类, small-小类", requiredMode = Schema.RequiredMode.REQUIRED, example = "big")
    @NotBlank(message = "类型层级不能为空")
    private String level;
}
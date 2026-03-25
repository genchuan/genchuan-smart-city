package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 窨井盖监测批量更新监测状态 Request VO")
@Data
public class BatchMonitorStatusReqVO {

    @Schema(description = "窨井盖 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "请选择要操作的井盖")
    private List<Long> coverIds;

    @Schema(description = "监测状态：运行中/已停止", requiredMode = Schema.RequiredMode.REQUIRED, example = "运行中")
    @NotEmpty(message = "监测状态不能为空")
    private String status;

}

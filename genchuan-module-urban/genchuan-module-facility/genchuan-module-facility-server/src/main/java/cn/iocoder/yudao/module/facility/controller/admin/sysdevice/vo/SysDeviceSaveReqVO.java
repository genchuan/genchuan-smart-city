package cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 设备新增/修改 Request VO")
@Data
public class SysDeviceSaveReqVO {

    @Schema(description = "[主键ID] 主键，设备唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "22461")
    private Long id;

    @Schema(description = "[设备编码] UUID格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[设备编码] UUID格式不能为空")
    private String deviceCode;

    @Schema(description = "[设备名称] 设备名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "[设备名称] 设备名称不能为空")
    private String name;

    @Schema(description = "[设备在线状态] 如:在线/离线/异常", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[设备在线状态] 如:在线/离线/异常不能为空")
    private String onlineStatus;

    @Schema(description = "[设备分类] 如道路设施监测等等")
    private String category;

    @Schema(description = "[设备描述] 设备描述", example = "你说的对")
    private String description;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}

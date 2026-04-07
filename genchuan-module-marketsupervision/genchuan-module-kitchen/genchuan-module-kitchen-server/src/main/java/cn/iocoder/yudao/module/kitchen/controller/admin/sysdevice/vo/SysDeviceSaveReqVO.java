package cn.iocoder.yudao.module.kitchen.controller.admin.sysdevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 设备信息新增/修改 Request VO")
@Data
public class SysDeviceSaveReqVO {

    @Schema(description = "[主键ID] 设备唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "23373")
    private Long id;

    @Schema(description = "[设备编号] 区域编码+设备类型+序号，唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[设备编号] 区域编码+设备类型+序号，唯一不能为空")
    private String deviceCode;

    @Schema(description = "[设备名称] 如：后厨摄像头、AI识别设备", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "[设备名称] 如：后厨摄像头、AI识别设备不能为空")
    private String deviceName;

    @Schema(description = "[设备类型] 如：摄像头、AI识别仪", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[设备类型] 如：摄像头、AI识别仪不能为空")
    private String deviceType;

    @Schema(description = "[所属企业ID] 关联enterprise_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18117")
    @NotEmpty(message = "[所属企业ID] 关联enterprise_info.id不能为空")
    private String entId;

    @Schema(description = "[所属区域ID] 关联sys_area.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14795")
    @NotNull(message = "[所属区域ID] 关联sys_area.id不能为空")
    private Long areaId;

    @Schema(description = "[状态] 如：在线/离线/故障/停用/维修中", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[状态] 如：在线/离线/故障/停用/维修中不能为空")
    private String status;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}

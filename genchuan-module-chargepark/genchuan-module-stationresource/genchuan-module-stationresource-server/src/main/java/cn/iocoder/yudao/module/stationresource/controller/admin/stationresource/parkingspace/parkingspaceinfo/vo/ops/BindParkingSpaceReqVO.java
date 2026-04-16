package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 车位绑定设备 Request VO")
@Data
public class BindParkingSpaceReqVO {

    @Schema(description = "车位ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车位ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "绑定设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "设备ID不能为空")
    private Long deviceId;
}

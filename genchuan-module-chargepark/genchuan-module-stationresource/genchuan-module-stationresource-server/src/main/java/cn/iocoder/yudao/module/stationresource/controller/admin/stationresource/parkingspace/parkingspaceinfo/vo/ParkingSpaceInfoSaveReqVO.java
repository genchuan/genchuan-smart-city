package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车位信息新增/修改 Request VO")
@Data
public class ParkingSpaceInfoSaveReqVO {

    @Schema(description = "[主键ID] 主键，BIGINT，自增，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "2145")
    private Long id;

    @Schema(description = "[车位编号] VARCHAR(32)，唯一，必填", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[车位编号] VARCHAR(32)，唯一，必填不能为空")
    private String spaceNo;

    @Schema(description = "[所属场站] 关联场站信息表station_info，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "14673")
    @NotNull(message = "[所属场站] 关联场站信息表station_info，必填不能为空")
    private Long stationId;

    @Schema(description = "[所属车库] VARCHAR(64)，必填", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[所属车库] VARCHAR(64)，必填不能为空")
    private String garage;

    @Schema(description = "[车位位置] VARCHAR(64)")
    private String location;

    @Schema(description = "[车位类型] 如：普通车位/充电车位，关联芋道字典表：parking_space_info_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[车位类型] 如：普通车位/充电车位，关联芋道字典表：parking_space_info_type不能为空")
    private String type;

    @Schema(description = "[设备类型] 如：地锁/充电桩/摄像头，关联芋道字典表：parking_space_info_device_type", example = "1")
    private String deviceType;

    @Schema(description = "[车位二维码] VARCHAR(255)")
    private String qrcode;

    @Schema(description = "[状态] 如：未绑定/已绑定/已禁用，关联芋道字典表：parking_space_info_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[状态] 如：未绑定/已绑定/已禁用，关联芋道字典表：parking_space_info_status不能为空")
    private String status;

    @Schema(description = "[实时状态] 如：空闲/占用/故障，关联芋道字典表：parking_space_info_real_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[实时状态] 如：空闲/占用/故障，关联芋道字典表：parking_space_info_real_status不能为空")
    private String realStatus;

    @Schema(description = "[绑定时间] DATETIME")
    private LocalDateTime bindTime;

    @Schema(description = "[绑定人] 关联芋道用户表system_user", example = "27418")
    private Long bindUserId;

    @Schema(description = "[绑定设备] BIGINT", example = "14479")
    private Long deviceId;

    @Schema(description = "[状态更新时间] DATETIME")
    private LocalDateTime statusUpdateTime;

    @Schema(description = "[备注] TEXT", example = "你猜")
    private String remark;

    @Schema(description = "[备用字段1] VARCHAR(100)")
    private String reserve1;

    @Schema(description = "[备用字段2] VARCHAR(100)")
    private String reserve2;

}

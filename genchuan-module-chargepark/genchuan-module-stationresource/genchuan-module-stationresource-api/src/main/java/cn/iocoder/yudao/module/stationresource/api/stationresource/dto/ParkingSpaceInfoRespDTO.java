package cn.iocoder.yudao.module.stationresource.api.stationresource.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 车位信息 RPC 响应 DTO（完整版）
 *
 * <p>包含 parking_space_info 全部字段
 */
@Schema(description = "RPC - 车位信息响应（完整版）")
@Data
public class ParkingSpaceInfoRespDTO {

    @Schema(description = "车位 ID")
    private Long id;

    @Schema(description = "车位编号（如 PS001）")
    private String spaceNo;

    @Schema(description = "所属场站 ID（关联 station_info）")
    private Long stationId;

    @Schema(description = "所属车库")
    private String garage;

    @Schema(description = "车位位置")
    private String location;

    @Schema(description = "车位类型（如：普通车位/充电车位）")
    private String type;

    @Schema(description = "设备类型（如：地锁/充电桩/摄像头）")
    private String deviceType;

    @Schema(description = "车位二维码")
    private String qrcode;

    @Schema(description = "状态（如：未绑定/已绑定/已禁用）")
    private String status;

    @Schema(description = "实时状态（如：空闲/占用/故障）")
    private String realStatus;

    @Schema(description = "绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "绑定人 ID（关联 system_user）")
    private Long bindUserId;

    @Schema(description = "绑定设备 ID")
    private Long deviceId;

    @Schema(description = "状态更新时间")
    private LocalDateTime statusUpdateTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}

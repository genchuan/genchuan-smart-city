package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;



@Schema(description = "管理后台 - 窨井盖监测数据分页查询 Response VO")
@Data
public class ManholeCoverRealTimePageRespVO {

    @Schema(description = "监测数据主键ID（UUID）", example = "f4g5h6i7-j8k9-0123-fghi-3456789abcde")
    private String id;

    @Schema(description = "窨井盖唯一ID", example = "c1d2e3f4-g5h6-7890-cdef-0123456789ab")
    private String coverId;

    @Schema(description = "窨井盖编码", example = "MC-2025-00189")
    private String coverCode;

    @Schema(description = "井盖名称", example = "XX大道与YY路交叉口东侧井盖")
    private String coverName;

    @Schema(description = "所属区域ID", example = "d2e3f4g5-h6i7-8901-defg-123456789abc")
    private String areaId;

    @Schema(description = "所属区域名称", example = "XX区XX街道")
    private String areaName;

    @Schema(description = "井盖状态", example = "2")
    private Integer coverStatus;

    @Schema(description = "井盖状态名称", example = "位移")
    private String coverStatusName;

    @Schema(description = "倾斜角度", example = "5.2°")
    private String tiltAngle;

    @Schema(description = "位移距离", example = "8cm")
    private String displacement;

    @Schema(description = "井内水位", example = "12cm")
    private String waterLevel;

    @Schema(description = "是否开启 (0-否，1-是)", example = "0")
    private Integer isOpen;

    @Schema(description = "设备电量（百分比）", example = "85")
    private Integer batteryLevel;

    @Schema(description = "设备信号强度", example = "-75dBm")
    private String signalStrength;

    @Schema(description = "数据采集时间", example = "2025-04-01 15:30:22")
    private String collectTime;

    @Schema(description = "监测数据区块链存证哈希", example = "0x123abc456def789ghi012jkl345mno678pqr901")
    private String chainHash;

    @Schema(description = "租户ID", example = "e3f4g5h6-i7j8-9012-efgh-23456789abcd")
    private String tenantId;

}
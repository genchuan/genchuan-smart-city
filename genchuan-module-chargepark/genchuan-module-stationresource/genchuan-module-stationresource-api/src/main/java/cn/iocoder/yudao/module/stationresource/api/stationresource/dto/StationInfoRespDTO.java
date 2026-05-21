package cn.iocoder.yudao.module.stationresource.api.stationresource.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 场站信息 RPC 响应 DTO（完整版）
 *
 * <p>包含 station_info 全部字段 + 片区坐标 + 空位数计算值
 */
@Schema(description = "RPC - 场站信息响应（完整版）")
@Data
public class StationInfoRespDTO {

    @Schema(description = "场站 ID")
    private Long id;

    @Schema(description = "场站编号")
    private String stationNo;

    @Schema(description = "场站名称")
    private String name;

    @Schema(description = "场站类型（如：公共/商业/小区/产业）")
    private String type;

    @Schema(description = "场站地址")
    private String address;

    @Schema(description = "泊位总数")
    private Integer spaceTotal;

    @Schema(description = "负责人 ID（关联 system_user）")
    private Long userId;

    @Schema(description = "收费标准")
    private String feeStandard;

    @Schema(description = "所属片区 ID（关联 area_info）")
    private Long areaId;

    @Schema(description = "运营类型（如：直接管理/甲方代运营）")
    private String operateType;

    @Schema(description = "状态（如：未生效/已生效/已禁用）")
    private String status;

    @Schema(description = "绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "绑定人 ID（关联 system_user）")
    private Long bindUserId;

    @Schema(description = "设备绑定数")
    private Integer deviceCount;

    @Schema(description = "车位绑定数")
    private Integer spaceCount;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    // ==================== 跨表补充（来自 area_info） ====================

    @Schema(description = "片区名称")
    private String areaName;

    @Schema(description = "经度（来自片区坐标）")
    private BigDecimal lon;

    @Schema(description = "纬度（来自片区坐标）")
    private BigDecimal lat;

    // ==================== 计算值 ====================

    @Schema(description = "空位数（spaceTotal - spaceCount，服务端计算）")
    private Integer emptySpace;
}

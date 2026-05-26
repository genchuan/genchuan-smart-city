package cn.iocoder.yudao.module.stationresource.api.stationresource.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 片区信息 RPC 响应 DTO（完整版，与分页 RespVO 对齐）
 */
@Schema(description = "RPC - 片区信息响应（完整版）")
@Data
public class AreaInfoRespDTO {

    @Schema(description = "片区 ID")
    private Long id;

    @Schema(description = "片区编号")
    private String areaNo;

    @Schema(description = "片区名称")
    private String name;

    @Schema(description = "上级片区 ID")
    private Long parentId;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "所属行政区划")
    private String district;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "负责人 ID（关联 system_user）")
    private Long leaderId;

    @Schema(description = "负责人名称")
    private String leaderName;

    @Schema(description = "负责人 ID（关联 system_user）")
    private Long userId;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "关联场站数")
    private Integer stationCount;

    @Schema(description = "状态（如：未生效/已生效/已禁用）")
    private String status;

    @Schema(description = "绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "绑定人 ID（关联 system_user）")
    private Long bindUserId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    // ==================== BaseDO 审计字段 ====================

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    // ==================== 坐标 ====================

    @Schema(description = "经度")
    private Double lon;

    @Schema(description = "纬度")
    private Double lat;
}

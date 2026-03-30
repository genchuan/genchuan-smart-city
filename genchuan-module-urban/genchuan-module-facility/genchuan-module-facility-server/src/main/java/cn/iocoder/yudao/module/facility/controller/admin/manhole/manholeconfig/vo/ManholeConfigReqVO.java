package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 窨井盖监测配置请求VO（新增/编辑）
 *
 * @author genchuan
 * @date 2026-03-18
 */
@Data
@Schema(description = "窨井盖监测配置请求参数")
public class ManholeConfigReqVO {

    @Schema(description = "主键ID（编辑时传）")
    private Long id;

    @Schema(description = "井盖编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String coverNo;

    @Schema(description = "路段ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long roadId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long deviceId;

    @Schema(description = "数据采集频率（秒）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer collectFrequency;

    @Schema(description = "倾斜角度阈值（度）", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal tiltAngleThreshold;

    @Schema(description = "运维员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long staffId;

    @Schema(description = "配置ID（编辑时传）")
    private Long configId;

    @Schema(description = "风险等级ID（默认1-低风险）")
    private Long riskLevelId = 1L;

    @Schema(description = "设备编号（用于唯一性校验）")
    private String deviceCode;
}
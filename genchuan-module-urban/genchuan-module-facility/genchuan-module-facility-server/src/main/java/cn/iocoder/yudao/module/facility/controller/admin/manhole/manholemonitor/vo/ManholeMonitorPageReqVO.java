package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 窨井盖监测分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ManholeMonitorPageReqVO extends PageParam {

    @Schema(description = "关联窨井盖表manhole_cover的id", example = "11309")
    private Long coverId;

    @Schema(description = "关联设备表sys_device的id", example = "26002")
    private Long deviceId;

    @Schema(description = "关联用户表sys_user的id（运维员）", example = "3128")
    private Long staffId;

    @Schema(description = "关联开合状态表sys_open_status的id", example = "23671")
    private Long openStatusId;

    @Schema(description = "倾斜角度（数值，单位：度）")
    private BigDecimal tiltAngle;

    @Schema(description = "振动数据（数值，单位：m/s²）")
    private BigDecimal vibrationData;

    @Schema(description = "关联风险等级表sys_risk_level的id", example = "29862")
    private Long riskLevelId;

    @Schema(description = "监测状态：运行中/已停止", example = "1")
    private String monitorStatus;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
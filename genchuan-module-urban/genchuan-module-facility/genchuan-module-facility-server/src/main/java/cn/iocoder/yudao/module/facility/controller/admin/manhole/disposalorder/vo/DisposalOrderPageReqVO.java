package cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 处置工单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DisposalOrderPageReqVO extends PageParam {

    @Schema(description = "关联告警表sys_warn的warn_id", example = "6209")
    private Long warnId;

    @Schema(description = "关联窨井盖表manhole_cover的id", example = "19667")
    private Long coverId;

    @Schema(description = "异常类型：倾斜/振动/开合异常", example = "1")
    private String abnormalType;

    @Schema(description = "关联风险等级表sys_risk_level的id", example = "29172")
    private Long riskLevelId;

    @Schema(description = "关联用户表sys_user的id（指派运维员）", example = "8015")
    private Long assignStaffId;

    @Schema(description = "工单创建时间（自动生成）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "处置时限（日期）")
    private LocalDate dealLimit;

    @Schema(description = "处置进度：待处置/现场处置/处置中/已完成", example = "2")
    private String processStatus;

    @Schema(description = "工单完成时间（日期）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] completeTime;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
package cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 处置工单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DisposalOrderRespVO {

    @Schema(description = "主键（自增）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1576")
    @ExcelProperty("主键（自增）")
    private Long id;

    @Schema(description = "关联告警表sys_warn的warn_id", example = "6209")
    @ExcelProperty("关联告警表sys_warn的warn_id")
    private Long warnId;

    @Schema(description = "关联窨井盖表manhole_cover的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19667")
    @ExcelProperty("关联窨井盖表manhole_cover的id")
    private Long coverId;

    @Schema(description = "异常类型：倾斜/振动/开合异常", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("异常类型：倾斜/振动/开合异常")
    private String abnormalType;

    @Schema(description = "关联风险等级表sys_risk_level的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29172")
    @ExcelProperty("关联风险等级表sys_risk_level的id")
    private Long riskLevelId;

    @Schema(description = "关联用户表sys_user的id（指派运维员）", requiredMode = Schema.RequiredMode.REQUIRED, example = "8015")
    @ExcelProperty("关联用户表sys_user的id（指派运维员）")
    private Long assignStaffId;

    @Schema(description = "工单创建时间（自动生成）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("工单创建时间（自动生成）")
    private LocalDateTime createTime;

    @Schema(description = "处置时限（日期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置时限（日期）")
    private LocalDate dealLimit;

    @Schema(description = "处置进度：待处置/现场处置/处置中/已完成", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("处置进度：待处置/现场处置/处置中/已完成")
    private String processStatus;

    @Schema(description = "工单完成时间（日期）")
    @ExcelProperty("工单完成时间（日期）")
    private LocalDate completeTime;

    @Schema(description = "[通用扩展字段1] 预留")
    @ExcelProperty("[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    @ExcelProperty("[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    @ExcelProperty("[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    @ExcelProperty("[通用扩展字段4] 预留")
    private String extCommon4;

}
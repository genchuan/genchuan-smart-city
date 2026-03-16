package cn.iocoder.yudao.module.envirhealth.controller.admin.task.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 任务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TaskRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13533")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "32463")
    @ExcelProperty("主键（UUID）")
    private String taskId;

    @Schema(description = "关联sys_task_type.sys_task_type_id", example = "3707")
    @ExcelProperty("关联sys_task_type.sys_task_type_id")
    private String taskTypeId;

    @Schema(description = "关联public_toilet.id（公厕相关任务）", example = "21219")
    @ExcelProperty("关联public_toilet.id（公厕相关任务）")
    private String toiletId;

    @Schema(description = "关联garbage_transfer.id（转运站相关任务）", example = "29108")
    @ExcelProperty("关联garbage_transfer.id（转运站相关任务）")
    private String transferId;

    @Schema(description = "关联public_institution.id（公共机构相关任务）", example = "30423")
    @ExcelProperty("关联public_institution.id（公共机构相关任务）")
    private String institutionId;

    @Schema(description = "关联commercial_street.id（商业街相关任务）", example = "7066")
    @ExcelProperty("关联commercial_street.id（商业街相关任务）")
    private String streetId;

    @Schema(description = "关联park.id（公园相关任务）", example = "20277")
    @ExcelProperty("关联park.id（公园相关任务）")
    private String parkId;

    @Schema(description = "关联urban_village.id（城中村相关任务）", example = "5313")
    @ExcelProperty("关联urban_village.id（城中村相关任务）")
    private String villageId;

    @Schema(description = "关联market.id（集贸市场相关任务）", example = "31868")
    @ExcelProperty("关联market.id（集贸市场相关任务）")
    private String marketId;

    @Schema(description = "关联river.id（河道相关任务）", example = "13896")
    @ExcelProperty("关联river.id（河道相关任务）")
    private String riverId;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "关联sys_user.id（处置人员）")
    @ExcelProperty("关联sys_user.id（处置人员）")
    private String handleBy;

    @Schema(description = "处置结果")
    @ExcelProperty("处置结果")
    private String handleResult;

    @Schema(description = "佐证材料URL（照片/文档链接，多个用逗号分隔）", example = "https://www.iocoder.cn")
    @ExcelProperty("佐证材料URL（照片/文档链接，多个用逗号分隔）")
    private String proofUrl;

    @Schema(description = "完成时间")
    @ExcelProperty("完成时间")
    private LocalDateTime completeTime;

    @Schema(description = "任务耗时（单位：分钟）")
    @ExcelProperty("任务耗时（单位：分钟）")
    private BigDecimal handleDuration;

    @Schema(description = "统计周期（可选值：日/周/月）")
    @ExcelProperty("统计周期（可选值：日/周/月）")
    private String statPeriod;

    @Schema(description = "保洁达标率（仅保洁类任务）")
    @ExcelProperty("保洁达标率（仅保洁类任务）")
    private BigDecimal cleaningQualifiedRate;

    @Schema(description = "问题办结率（仅问题处置类任务）")
    @ExcelProperty("问题办结率（仅问题处置类任务）")
    private BigDecimal problemCompleteRate;

    @Schema(description = "核查通过率（仅核查类任务）")
    @ExcelProperty("核查通过率（仅核查类任务）")
    private BigDecimal inspectionPassRate;

    @Schema(description = "进站总量（仅转运作业类任务，单位：吨）")
    @ExcelProperty("进站总量（仅转运作业类任务，单位：吨）")
    private BigDecimal totalEntryVolume;

    @Schema(description = "设备完好率（仅维护类任务）")
    @ExcelProperty("设备完好率（仅维护类任务）")
    private BigDecimal equipmentIntactRate;

    @Schema(description = "环境达标率（仅预警类任务）")
    @ExcelProperty("环境达标率（仅预警类任务）")
    private BigDecimal environmentQualifiedRate;

    @Schema(description = "满意度（仅投诉类任务，可选值：满意/基本满意/不满意）")
    @ExcelProperty("满意度（仅投诉类任务，可选值：满意/基本满意/不满意）")
    private String satisfaction;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
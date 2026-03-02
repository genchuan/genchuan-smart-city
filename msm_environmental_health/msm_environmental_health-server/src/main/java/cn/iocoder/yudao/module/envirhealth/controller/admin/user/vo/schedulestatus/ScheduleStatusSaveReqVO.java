package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedulestatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 排班状态字典表【通用复用】新增/修改 Request VO")
@Data
public class ScheduleStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7577")
    private Long id;

    @Schema(description = "主键（UUID）", example = "25925")
    private String scheduleStatusId;

    @Schema(description = "排班状态名称：待执行/执行中/已完成/已取消", example = "李四")
    private String scheduleStatusName;

    @Schema(description = "描述", example = "你猜")
    private String description;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "2")
    private Integer status;

    @Schema(description = "排序值")
    private Integer sort;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
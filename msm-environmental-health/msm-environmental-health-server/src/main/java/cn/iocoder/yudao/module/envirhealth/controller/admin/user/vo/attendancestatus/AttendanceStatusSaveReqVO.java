package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendancestatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 考勤状态字典表新增/修改 Request VO")
@Data
public class AttendanceStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32226")
    private Long id;

    @Schema(description = "主键（UUID）", example = "17160")
    private String attendanceStatusId;

    @Schema(description = "考勤状态名称：正常/异常/未打卡", example = "李四")
    private String attendanceStatusName;

    @Schema(description = "描述", example = "你说的对")
    private String description;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "1")
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
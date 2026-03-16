package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendanceabnormaltype;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 考勤异常类型字典表新增/修改 Request VO")
@Data
public class AttendanceAbnormalTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25676")
    private Long id;

    @Schema(description = "主键（UUID）", example = "19971")
    private String attendanceAbnormalTypeId;

    @Schema(description = "异常类型名称：迟到/早退/旷工/未打卡/定位异常", example = "张三")
    private String abnormalTypeName;

    @Schema(description = "描述", example = "随便")
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
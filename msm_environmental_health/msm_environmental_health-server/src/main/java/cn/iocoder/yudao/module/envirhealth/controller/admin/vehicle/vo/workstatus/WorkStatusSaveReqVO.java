package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.workstatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 作业状态字典表【通用复用】新增/修改 Request VO")
@Data
public class WorkStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22764")
    private Long id;

    @Schema(description = "主键（UUID）", example = "6363")
    private String workStatusId;

    @Schema(description = "作业状态名称：作业中/暂停/已完成/异常", example = "张三")
    private String workStatusName;

    @Schema(description = "描述", example = "你猜")
    private String description;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationstatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 违规状态字典表【通用复用】新增/修改 Request VO")
@Data
public class ViolationStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2087")
    private Long id;

    @Schema(description = "主键（UUID）", example = "4899")
    private String violationStatusId;

    @Schema(description = "违规状态名称：待处理/整改中/已办结/重新整改", example = "芋艿")
    private String violationStatusName;

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
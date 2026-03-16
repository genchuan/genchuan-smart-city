package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationtype;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 违规类型字典表【通用复用】新增/修改 Request VO")
@Data
public class ViolationTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29019")
    private Long id;

    @Schema(description = "主键（UUID）", example = "13123")
    private String violationTypeId;

    @Schema(description = "违规类型名称", example = "芋艿")
    private String violationName;

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
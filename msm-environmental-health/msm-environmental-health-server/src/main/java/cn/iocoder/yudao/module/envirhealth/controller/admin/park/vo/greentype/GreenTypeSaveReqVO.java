package cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.greentype;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 绿化品类字典表【通用复用】新增/修改 Request VO")
@Data
public class GreenTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8595")
    private Long id;

    @Schema(description = "主键（UUID）", example = "282")
    private String greenTypeId;

    @Schema(description = "绿化品类名称", example = "赵六")
    private String greenName;

    @Schema(description = "养护要求")
    private String maintenanceRequire;

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
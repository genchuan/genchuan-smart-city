package cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.cleaningtype;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 保洁类型字典表新增/修改 Request VO")
@Data
public class CleaningTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7544")
    private Long id;

    @Schema(description = "主键（UUID）", example = "29352")
    private String cleaningTypeId;

    @Schema(description = "保洁类型名称：水域/陆域", example = "赵六")
    private String cleaningName;

    @Schema(description = "描述", example = "你猜")
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
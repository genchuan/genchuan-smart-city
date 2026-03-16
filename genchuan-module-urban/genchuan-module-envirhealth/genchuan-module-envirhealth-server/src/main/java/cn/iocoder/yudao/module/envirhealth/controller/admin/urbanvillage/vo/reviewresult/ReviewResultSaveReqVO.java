package cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 复核结果字典表【通用复用】新增/修改 Request VO")
@Data
public class ReviewResultSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23889")
    private Long id;

    @Schema(description = "主键（UUID）", example = "6524")
    private String reviewResultId;

    @Schema(description = "复核结果名称：通过/不通过", example = "张三")
    private String reviewResultName;

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
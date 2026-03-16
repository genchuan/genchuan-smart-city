package cn.iocoder.yudao.module.evaluate.controller.admin.sys.scope.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 范围字典新增/修改 Request VO")
@Data
public class ScopeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12793")
    private Long id;

    @Schema(description = "范围ID（UUID）", example = "24700")
    private String scopeId;

    @Schema(description = "范围名称", example = "芋艿")
    private String name;

    @Schema(description = "范围编码")
    private String code;

    @Schema(description = "范围描述")
    private String desc;

    @Schema(description = "业务创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
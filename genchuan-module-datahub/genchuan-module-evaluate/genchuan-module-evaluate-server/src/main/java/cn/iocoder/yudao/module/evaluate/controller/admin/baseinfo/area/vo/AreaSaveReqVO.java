package cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.area.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 区域编码新增/修改 Request VO")
@Data
public class AreaSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17233")
    private Long id;

    @Schema(description = "区域编码（业务主键）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "区域编码（业务主键）不能为空")
    private String areaCode;

    @Schema(description = "区域名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "区域名称不能为空")
    private String areaName;

    @Schema(description = "上级区域编码（关联sys_area.area_code）")
    private String parentCode;

    @Schema(description = "区域层级（1-省级，2-市级，3-区级/县级等）")
    private Integer level;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "17097")
    private Integer statusId;

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
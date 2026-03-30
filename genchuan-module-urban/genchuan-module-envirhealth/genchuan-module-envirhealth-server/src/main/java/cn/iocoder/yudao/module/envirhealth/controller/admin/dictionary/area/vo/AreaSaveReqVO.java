package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.area.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 区域编码新增/修改 Request VO")
@Data
public class AreaSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12097")
    private Long id;

    @Schema(description = "主键（区域编码）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主键（区域编码）不能为空")
    private String areaCode;

    @Schema(description = "区域名称", example = "张三")
    private String areaName;

    @Schema(description = "上级区域编码（关联sys_area.area_code，顶级区域填0）")
    private String parentCode;

    @Schema(description = "区域层级（可选值：1-省级/2-市级/3-区级/4-街道/5-社区）")
    private Integer level;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
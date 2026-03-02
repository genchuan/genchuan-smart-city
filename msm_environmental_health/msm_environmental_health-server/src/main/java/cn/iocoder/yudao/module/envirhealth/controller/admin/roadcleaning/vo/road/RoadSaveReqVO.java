package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.road;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 道路新增/修改 Request VO")
@Data
public class RoadSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3573")
    private Long id;

    @Schema(description = "道路主键（UUID）", example = "20896")
    private String roadId;

    @Schema(description = "道路名称", example = "王五")
    private String roadName;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "道路等级")
    private String roadLevel;

    @Schema(description = "长度，单位：公里")
    private BigDecimal length;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
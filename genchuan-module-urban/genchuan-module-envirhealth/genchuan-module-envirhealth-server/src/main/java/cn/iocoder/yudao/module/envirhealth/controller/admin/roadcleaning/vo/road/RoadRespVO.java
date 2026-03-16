package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.road;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 道路 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3573")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "道路主键（UUID）", example = "20896")
    @ExcelProperty("道路主键（UUID）")
    private String roadId;

    @Schema(description = "道路名称", example = "王五")
    @ExcelProperty("道路名称")
    private String roadName;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "道路等级")
    @ExcelProperty("道路等级")
    private String roadLevel;

    @Schema(description = "长度，单位：公里")
    @ExcelProperty("长度，单位：公里")
    private BigDecimal length;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
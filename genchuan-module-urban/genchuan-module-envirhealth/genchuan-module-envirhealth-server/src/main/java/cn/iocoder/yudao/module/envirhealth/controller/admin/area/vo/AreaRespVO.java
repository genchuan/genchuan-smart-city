package cn.iocoder.yudao.module.envirhealth.controller.admin.area.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 区域编码 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AreaRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12097")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "主键（区域编码）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键（区域编码）")
    private String areaCode;

    @Schema(description = "区域名称", example = "张三")
    @ExcelProperty("区域名称")
    private String areaName;

    @Schema(description = "上级区域编码（关联sys_area.area_code，顶级区域填0）")
    @ExcelProperty("上级区域编码（关联sys_area.area_code，顶级区域填0）")
    private String parentCode;

    @Schema(description = "区域层级（可选值：1-省级/2-市级/3-区级/4-街道/5-社区）")
    @ExcelProperty("区域层级（可选值：1-省级/2-市级/3-区级/4-街道/5-社区）")
    private Integer level;

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
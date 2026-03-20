package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.greentype.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 绿化品类字典表【通用复用】 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GreenTypeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8595")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "282")
    @ExcelProperty("主键（UUID）")
    private String greenTypeId;

    @Schema(description = "绿化品类名称", example = "赵六")
    @ExcelProperty("绿化品类名称")
    private String greenName;

    @Schema(description = "养护要求")
    @ExcelProperty("养护要求")
    private String maintenanceRequire;

    @Schema(description = "描述", example = "你猜")
    @ExcelProperty("描述")
    private String description;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "2")
    @ExcelProperty("状态（可选值：0-禁用/1-启用）")
    private Integer status;

    @Schema(description = "排序值")
    @ExcelProperty("排序值")
    private Integer sort;

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
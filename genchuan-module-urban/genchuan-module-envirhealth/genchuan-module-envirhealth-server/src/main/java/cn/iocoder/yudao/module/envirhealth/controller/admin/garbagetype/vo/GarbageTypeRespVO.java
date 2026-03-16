package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 垃圾品类字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GarbageTypeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12817")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "25568")
    @ExcelProperty("业务主键（UUID）")
    private String sysGarbageTypeId;

    @Schema(description = "品类名称", example = "王五")
    @ExcelProperty("品类名称")
    private String name;

    @Schema(description = "品类编码")
    @ExcelProperty("品类编码")
    private String code;

    @Schema(description = "状态：启用/禁用", example = "2")
    @ExcelProperty("状态：启用/禁用")
    private Integer status;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

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
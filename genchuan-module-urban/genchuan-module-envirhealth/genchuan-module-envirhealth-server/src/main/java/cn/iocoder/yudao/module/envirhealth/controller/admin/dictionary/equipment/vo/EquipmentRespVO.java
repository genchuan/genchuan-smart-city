package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.equipment.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EquipmentRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12119")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "14944")
    @ExcelProperty("业务主键（UUID）")
    private String sysEquipmentId;

    @Schema(description = "设备名称", example = "赵六")
    @ExcelProperty("设备名称")
    private String name;

    @Schema(description = "设备编码")
    @ExcelProperty("设备编码")
    private String code;

    @Schema(description = "设备类型", example = "2")
    @ExcelProperty("设备类型")
    private String type;

    @Schema(description = "设备型号")
    @ExcelProperty("设备型号")
    private String model;

    @Schema(description = "规格参数")
    @ExcelProperty("规格参数")
    private String specification;

    @Schema(description = "维护周期（单位：天）")
    @ExcelProperty("维护周期（单位：天）")
    private Integer maintenanceCycle;

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
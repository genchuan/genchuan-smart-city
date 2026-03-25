package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.alarmtype.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警类型字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AlarmTypeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27030")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "预警类型主键（UUID）", example = "14444")
    @ExcelProperty("预警类型主键（UUID）")
    private String alarmTypeId;

    @Schema(description = "预警类型名称", example = "张三")
    @ExcelProperty("预警类型名称")
    private String alarmName;

    @Schema(description = "描述", example = "随便")
    @ExcelProperty("描述")
    private String description;

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
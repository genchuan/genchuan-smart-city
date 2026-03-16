package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.consumable;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 耗材字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ConsumableRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5936")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "耗材主键（UUID）", example = "2627")
    @ExcelProperty("耗材主键（UUID）")
    private String consumableId;

    @Schema(description = "耗材名称", example = "芋艿")
    @ExcelProperty("耗材名称")
    private String consumableName;

    @Schema(description = "规格")
    @ExcelProperty("规格")
    private String specification;

    @Schema(description = "描述", example = "你说的对")
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
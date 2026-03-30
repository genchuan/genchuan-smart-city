package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.facility.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设施字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FacilityRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19902")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "2644")
    @ExcelProperty("业务主键（UUID）")
    private String sysFacilityId;

    @Schema(description = "设施名称", example = "李四")
    @ExcelProperty("设施名称")
    private String name;

    @Schema(description = "设施编码")
    @ExcelProperty("设施编码")
    private String code;

    @Schema(description = "设施类型", example = "1")
    @ExcelProperty("设施类型")
    private String type;

    @Schema(description = "状态：启用/禁用", example = "2")
    @ExcelProperty("状态：启用/禁用")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
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
package cn.iocoder.yudao.module.waterdetection.controller.admin.gb5749standard.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 《生活饮用水卫生标准》GB 5749-2022标准 Response VO")
@Data
@ExcelIgnoreUnannotated
public class Gb5749StandardRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "指标名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标名称")
    private String itemName;

    @Schema(description = "标准值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("标准值")
    private String limitValue;

    @Schema(description = "排序序号")
    @ExcelProperty("排序序号")
    private Integer itemOrder;

}
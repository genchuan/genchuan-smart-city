package cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridboundarystyle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 网格边界线样式配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridBoundaryStyleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "样式ID")
    @ExcelProperty("样式ID")
    private String styleId;

    @Schema(description = "比例尺", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("比例尺")
    private String scale;

    @Schema(description = "网格类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网格类型")
    private String gridType;

    @Schema(description = "线宽(mm)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("线宽(mm)")
    private BigDecimal lineWidth;

    @Schema(description = "颜色C值(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("颜色C值(%)")
    private BigDecimal colorC;

    @Schema(description = "颜色M值(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("颜色M值(%)")
    private BigDecimal colorM;

    @Schema(description = "颜色Y值(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("颜色Y值(%)")
    private BigDecimal colorY;

    @Schema(description = "颜色K值(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("颜色K值(%)")
    private BigDecimal colorK;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String createUserId;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
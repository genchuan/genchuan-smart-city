package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.unitgridattr.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 单元网格属性 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UnitGridAttrRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "属性ID")
    @ExcelProperty("属性ID")
    private String unitAttrId;

    @Schema(description = "单元网格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("单元网格ID")
    private String unitGridId;

    @Schema(description = "单元网格编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("单元网格编码")
    private String unitGridCode;

    @Schema(description = "面积(m²)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("面积(m²)")
    private Integer area;

    @Schema(description = "初始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("初始时间")
    private LocalDateTime initTime;

    @Schema(description = "终止时间")
    @ExcelProperty("终止时间")
    private LocalDateTime endTime;

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
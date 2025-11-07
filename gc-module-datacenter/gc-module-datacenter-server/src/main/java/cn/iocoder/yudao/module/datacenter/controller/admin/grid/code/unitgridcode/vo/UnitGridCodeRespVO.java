package cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 单元网格编码 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UnitGridCodeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5052")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "编码ID", example = "8933")
    @ExcelProperty("编码ID")
    private String unitCodeId;

    @Schema(description = "单元网格标识码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("单元网格标识码")
    private String unitGridCode;

    @Schema(description = "所属单元网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19876")
    @ExcelProperty("所属单元网格ID")
    private String unitGridId;

    @Schema(description = "行政区划完整代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划完整代码")
    private String areaFullCode;

    @Schema(description = "顺序码")
    @ExcelProperty("顺序码")
    private String seqCode;

    @Schema(description = "编码生成时间")
    @ExcelProperty("编码生成时间")
    private LocalDateTime generateTime;

    @Schema(description = "编码状态", example = "1")
    @ExcelProperty("编码状态")
    private String codeStatus;

    @Schema(description = "备注", example = "随便")
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
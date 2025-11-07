package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 网格数据质量检查 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridDataQualCkRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6244")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "质量检查ID", example = "10584")
    @ExcelProperty("质量检查ID")
    private String qualCkId;

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31757")
    @ExcelProperty("网格ID")
    private String gridId;

    @Schema(description = "网格类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("网格类型")
    private String gridType;

    @Schema(description = "网格编码")
    @ExcelProperty("网格编码")
    private String gridCode;

    @Schema(description = "检查项", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查项")
    private String ckItem;

    @Schema(description = "检查结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查结果")
    private String ckResult;

    @Schema(description = "错误描述")
    @ExcelProperty("错误描述")
    private String errorDesc;

    @Schema(description = "整改建议")
    @ExcelProperty("整改建议")
    private String rectifySugg;

    @Schema(description = "检查时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查时间")
    private LocalDateTime ckTime;

    @Schema(description = "检查人员", requiredMode = Schema.RequiredMode.REQUIRED, example = "25277")
    @ExcelProperty("检查人员")
    private String ckUserId;

    @Schema(description = "整改状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("整改状态")
    private String rectifyStatus;

    @Schema(description = "整改时间")
    @ExcelProperty("整改时间")
    private LocalDateTime rectifyTime;

    @Schema(description = "整改人员", example = "21785")
    @ExcelProperty("整改人员")
    private String rectifyUserId;

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
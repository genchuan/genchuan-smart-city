package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.extgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 扩展网格划分 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ExtGridDivRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14541")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "扩展网格ID", example = "395")
    @ExcelProperty("扩展网格ID")
    private String extGridId;

    @Schema(description = "扩展网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("扩展网格名称")
    private String extGridName;

    @Schema(description = "扩展网格类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("扩展网格类型")
    private String extType;

    @Schema(description = "所含基础网格IDs", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所含基础网格IDs")
    private String includedBasicIds;

    @Schema(description = "基础网格类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("基础网格类型")
    private String basicGridType;

    @Schema(description = "面积(m²)")
    @ExcelProperty("面积(m²)")
    private Integer area;

    @Schema(description = "边界坐标", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("边界坐标")
    private String boundaryCoords;

    @Schema(description = "申请原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不香")
    @ExcelProperty("申请原因")
    private String applyReason;

    @Schema(description = "审核状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("审核状态")
    private String auditStatus;

    @Schema(description = "申请人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20045")
    @ExcelProperty("申请人ID")
    private String applyUserId;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "审核人ID", example = "21987")
    @ExcelProperty("审核人ID")
    private String auditUserId;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核意见")
    @ExcelProperty("审核意见")
    private String auditOpinion;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
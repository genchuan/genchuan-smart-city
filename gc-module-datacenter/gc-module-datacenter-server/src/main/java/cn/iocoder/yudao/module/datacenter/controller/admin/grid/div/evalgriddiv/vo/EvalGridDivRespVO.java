package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 评价网格划分 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalGridDivRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "556")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "评价网格ID", example = "15263")
    @ExcelProperty("评价网格ID")
    private String evalGridId;

    @Schema(description = "评价网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("评价网格名称")
    private String evalGridName;

    @Schema(description = "所属乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10750")
    @ExcelProperty("所属乡镇ID")
    private String townStreetId;

    @Schema(description = "评价网格类型(A/B/C)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("评价网格类型(A/B/C)")
    private String gridType;

    @Schema(description = "所含管理网格IDs", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所含管理网格IDs")
    private String includedMgIds;

    @Schema(description = "面积(m²)")
    @ExcelProperty("面积(m²)")
    private Integer area;

    @Schema(description = "划分时间")
    @ExcelProperty("划分时间")
    private LocalDateTime divTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "备注", example = "你说的对")
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

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

    // 新增：边界坐标信息
    @Schema(description = "边界坐标信息列表")
    @ExcelIgnore
    private List<EvalGridBoundaryInfo> boundaryCoords;
}
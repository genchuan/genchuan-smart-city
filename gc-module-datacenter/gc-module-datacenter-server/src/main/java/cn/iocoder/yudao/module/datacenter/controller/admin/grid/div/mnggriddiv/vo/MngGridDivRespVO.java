package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv.vo.UnitGridBoundaryInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理网格划分 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MngGridDivRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23779")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "管理网格ID", example = "7217")
    @ExcelProperty("管理网格ID")
    private String mngGridId;

    @Schema(description = "管理网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("管理网格名称")
    private String mngGridName;

    @Schema(description = "所属乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10958")
    @ExcelProperty("所属乡镇ID")
    private String townStreetId;

    @Schema(description = "所含单元网格IDs", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所含单元网格IDs")
    private String includedUnitIds;

    @Schema(description = "面积(m²)")
    @ExcelProperty("面积(m²)")
    private Integer area;

    @Schema(description = "划分时间")
    @ExcelProperty("划分时间")
    private LocalDateTime divTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "备注", example = "你猜")
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

    @Schema(description = "关联的单元网格边界坐标列表")
    @ExcelIgnore
    private List<UnitGridBoundaryInfo> boundaryCoords;

}
package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

//@Schema(description = "管理后台 - 通用场景表，一级和二级场景 Response VO")
//@Data
//@ExcelIgnoreUnannotated
//public class UniversalSceneRespVO {
//
//    @Schema(description = "主键，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "2239")
//    @ExcelProperty("主键，自增")
//    private Long id;
//
//    @Schema(description = "场景唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "9162")
//    @ExcelProperty("场景唯一标识")
//    private String sceneCode;
//
//    @Schema(description = "父级ID，0表示一级场景", requiredMode = Schema.RequiredMode.REQUIRED, example = "26486")
//    @ExcelProperty("父级ID，0表示一级场景")
//    private Long parentId;
//
//    @Schema(description = "场景等级，一级场景为1，二级为2", requiredMode = Schema.RequiredMode.REQUIRED)
//    @ExcelProperty("场景等级，一级场景为1，二级为2")
//    private Integer level;
//
//    @Schema(description = "场景描述", example = "你猜")
//    @ExcelProperty("场景描述")
//    private String description;
//
//    @Schema(description = "场景名称，如 城管住建/市政设施", requiredMode = Schema.RequiredMode.REQUIRED)
//    @ExcelProperty("场景名称，如 城管住建/市政设施")
//    private String label;
//
//    @Schema(description = "场景值，用于前端选择，如 urban_admin / urban_admin_facility", requiredMode = Schema.RequiredMode.REQUIRED)
//    @ExcelProperty("场景值，用于前端选择，如 urban_admin / urban_admin_facility")
//    private String value;
//
//    @Schema(description = "创建时间")
//    @ExcelProperty("创建时间")
//    private LocalDateTime createTime;
//
//    @Schema(description = "分类扩展字段1")
//    @ExcelProperty("分类扩展字段1")
//    private String extCat1;
//
//    @Schema(description = "分类扩展字段2")
//    @ExcelProperty("分类扩展字段2")
//    private String extCat2;
//
//    @Schema(description = "通用扩展字段1")
//    @ExcelProperty("通用扩展字段1")
//    private String extCommon1;
//
//    @Schema(description = "通用扩展字段2")
//    @ExcelProperty("通用扩展字段2")
//    private String extCommon2;
//
//    @Schema(description = "子场景列表")
//    @ExcelIgnore
//    private List<UniversalSceneRespVO> children;
//
//}
@Schema(description = "管理后台 - 通用场景列表 Response VO")
@Data
public class UniversalSceneRespVO {

    @Schema(description = "场景唯一标识，用于前端选择", requiredMode = Schema.RequiredMode.REQUIRED, example = "urban_management")
    private String value;

    @Schema(description = "场景显示名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "城管住建")
    private String label;

    @Schema(description = "子场景列表，必填项", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<UniversalSceneRespVO> children;

    @Schema(description = "场景描述，二级场景展示详细说明", example = "包含路灯、井盖、消防栓等城市基础设施")
    private String desc;
}

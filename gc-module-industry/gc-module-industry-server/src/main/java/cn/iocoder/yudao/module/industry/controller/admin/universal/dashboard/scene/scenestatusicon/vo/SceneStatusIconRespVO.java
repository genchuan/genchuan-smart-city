package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenestatusicon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 场景状态字段图标配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SceneStatusIconRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24289")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "所属场景唯一标识码（sceneKey）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属场景唯一标识码（sceneKey）")
    private String sceneCode;

    @Schema(description = "状态字段唯一标识码（对应 selectedFieldsConfig.key）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("状态字段唯一标识码（对应 selectedFieldsConfig.key）")
    private String fieldCode;

    @Schema(description = "状态原始值（对应业务数据中的字段值）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("状态原始值（对应业务数据中的字段值）")
    private String rawValue;

    @Schema(description = "该状态对应的地图点位图标地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @ExcelProperty("该状态对应的地图点位图标地址")
    private String iconUrl;

    @Schema(description = "图例展示文本（legend 中显示的文字，可为空）")
    @ExcelProperty("图例展示文本（legend 中显示的文字，可为空）")
    private String legendText;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

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

}

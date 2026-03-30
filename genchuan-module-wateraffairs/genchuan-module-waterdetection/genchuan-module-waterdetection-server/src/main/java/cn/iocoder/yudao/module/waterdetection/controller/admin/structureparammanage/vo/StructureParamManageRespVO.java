package cn.iocoder.yudao.module.waterdetection.controller.admin.structureparammanage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 构建筑物参数管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StructureParamManageRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "构建筑物名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("构建筑物名称")
    private String structureName;

    @Schema(description = "类型(沉淀池/滤池/清水池等)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("类型(沉淀池/滤池/清水池等)")
    private String structureType;

    @Schema(description = "长度(米)")
    @ExcelProperty("长度(米)")
    private Double length;

    @Schema(description = "宽度(米)")
    @ExcelProperty("宽度(米)")
    private Double width;

    @Schema(description = "深度(米)")
    @ExcelProperty("深度(米)")
    private Double depth;

    @Schema(description = "有效容积(立方米)")
    @ExcelProperty("有效容积(立方米)")
    private Double effectiveVolume;

    @Schema(description = "建设时间")
    @ExcelProperty("建设时间")
    private LocalDateTime constructionTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
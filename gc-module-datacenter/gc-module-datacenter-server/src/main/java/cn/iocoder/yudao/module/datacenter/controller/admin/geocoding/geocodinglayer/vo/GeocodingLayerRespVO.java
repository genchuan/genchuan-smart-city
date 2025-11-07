package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinglayer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 图层代码配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GeocodingLayerRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "配置ID")
    @ExcelProperty("配置ID")
    private String layerConfigId;

    @Schema(description = "图层代码")
    @ExcelProperty("图层代码")
    private String layerCode;

    @Schema(description = "图层名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("图层名称")
    private String layerName;

    @Schema(description = "图层说明")
    @ExcelProperty("图层说明")
    private String layerDesc;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    @ExcelProperty("启用状态(0-禁用,1-启用)")
    private Integer enableStatus;

    @Schema(description = "配置人")
    @ExcelProperty("配置人")
    private String configUser;

    @Schema(description = "配置时间")
    @ExcelProperty("配置时间")
    private LocalDateTime configTime;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    @ExcelProperty("分类扩展字段3")
    private String extCategory3;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstreet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 街巷数据管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GeocodingStreetRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "街巷ID")
    @ExcelProperty("街巷ID")
    private String streetId;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("名称")
    private String streetName;

    @Schema(description = "标识码")
    @ExcelProperty("标识码")
    private String streetCode;

    @Schema(description = "别名")
    @ExcelProperty("别名")
    private String alias;

    @Schema(description = "曾用名")
    @ExcelProperty("曾用名")
    private String oldName;

    @Schema(description = "最小门牌号")
    @ExcelProperty("最小门牌号")
    private String minHouseNum;

    @Schema(description = "最大门牌号")
    @ExcelProperty("最大门牌号")
    private String maxHouseNum;

    @Schema(description = "起点名称")
    @ExcelProperty("起点名称")
    private String beginningName;

    @Schema(description = "止点名称")
    @ExcelProperty("止点名称")
    private String endingName;

    @Schema(description = "走向")
    @ExcelProperty("走向")
    private String direction;

    @Schema(description = "中心线坐标串")
    @ExcelProperty("中心线坐标串")
    private String centerLineCoords;

    @Schema(description = "数据来源")
    @ExcelProperty("数据来源")
    private String dataSource;

    @Schema(description = "初始日期")
    @ExcelProperty("初始日期")
    private LocalDateTime initDate;

    @Schema(description = "变更日期")
    @ExcelProperty("变更日期")
    private LocalDateTime updateDate;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

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
package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingpoi.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 兴趣点数据管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GeocodingPoiRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "兴趣点ID")
    @ExcelProperty("兴趣点ID")
    private String poiId;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("名称")
    private String poiName;

    @Schema(description = "标识码")
    @ExcelProperty("标识码")
    private String poiCode;

    @Schema(description = "内部编码")
    @ExcelProperty("内部编码")
    private String intraCode;

    @Schema(description = "北斗网格位置码")
    @ExcelProperty("北斗网格位置码")
    private String bdGridCode;

    @Schema(description = "曾用名")
    @ExcelProperty("曾用名")
    private String oldName;

    @Schema(description = "所在层")
    @ExcelProperty("所在层")
    private String floor;

    @Schema(description = "位置点坐标X")
    @ExcelProperty("位置点坐标X")
    private BigDecimal coordX;

    @Schema(description = "位置点坐标Y")
    @ExcelProperty("位置点坐标Y")
    private BigDecimal coordY;

    @Schema(description = "所属类型")
    @ExcelProperty("所属类型")
    private String poiType;

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
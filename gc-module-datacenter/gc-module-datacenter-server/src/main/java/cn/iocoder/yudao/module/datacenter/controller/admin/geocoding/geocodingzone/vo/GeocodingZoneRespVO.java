package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingzone.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 地片与区片数据管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GeocodingZoneRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "地片区片ID")
    @ExcelProperty("地片区片ID")
    private String zoneId;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("名称")
    private String zoneName;

    @Schema(description = "标识码")
    @ExcelProperty("标识码")
    private String zoneCode;

    @Schema(description = "别名")
    @ExcelProperty("别名")
    private String alias;

    @Schema(description = "曾用名")
    @ExcelProperty("曾用名")
    private String oldName;

    @Schema(description = "四至范围")
    @ExcelProperty("四至范围")
    private String boundary;

    @Schema(description = "所在街道名称")
    @ExcelProperty("所在街道名称")
    private String streetName;

    @Schema(description = "所在街道代码")
    @ExcelProperty("所在街道代码")
    private String streetCode;

    @Schema(description = "所在社区名称")
    @ExcelProperty("所在社区名称")
    private String communityName;

    @Schema(description = "所在社区代码")
    @ExcelProperty("所在社区代码")
    private String communityCode;

    @Schema(description = "空间表示类型", example = "2")
    @ExcelProperty("空间表示类型")
    private String spaceType;

    @Schema(description = "标识点坐标X")
    @ExcelProperty("标识点坐标X")
    private BigDecimal coordX;

    @Schema(description = "标识点坐标Y")
    @ExcelProperty("标识点坐标Y")
    private BigDecimal coordY;

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
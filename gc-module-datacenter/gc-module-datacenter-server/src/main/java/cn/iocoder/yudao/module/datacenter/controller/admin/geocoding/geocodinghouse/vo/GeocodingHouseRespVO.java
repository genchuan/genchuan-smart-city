package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinghouse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 门(楼)牌数据管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GeocodingHouseRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "门牌楼牌ID")
    @ExcelProperty("门牌楼牌ID")
    private String houseId;

    @Schema(description = "名称")
    @ExcelProperty("名称")
    private String houseName;

    @Schema(description = "标识码")
    @ExcelProperty("标识码")
    private String houseCode;

    @Schema(description = "北斗网格位置码")
    @ExcelProperty("北斗网格位置码")
    private String bdGridCode;

    @Schema(description = "所在街巷")
    @ExcelProperty("所在街巷")
    private String streetName;

    @Schema(description = "所在地片/区片")
    @ExcelProperty("所在地片/区片")
    private String zoneName;

    @Schema(description = "门(楼)牌号")
    @ExcelProperty("门(楼)牌号")
    private String houseNum;

    @Schema(description = "所在街道名称")
    @ExcelProperty("所在街道名称")
    private String parentStreetName;

    @Schema(description = "所在街道代码")
    @ExcelProperty("所在街道代码")
    private String streetCode;

    @Schema(description = "所在社区名称")
    @ExcelProperty("所在社区名称")
    private String communityName;

    @Schema(description = "所在社区代码")
    @ExcelProperty("所在社区代码")
    private String communityCode;

    @Schema(description = "位置点坐标X")
    @ExcelProperty("位置点坐标X")
    private BigDecimal coordX;

    @Schema(description = "位置点坐标Y")
    @ExcelProperty("位置点坐标Y")
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
package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingdensity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 数据采集密度配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GeocodingDensityRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14269")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "密度配置ID", example = "13375")
    @ExcelProperty("密度配置ID")
    private String densityConfigId;

    @Schema(description = "区域类别", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("区域类别")
    private String regionCategory;

    @Schema(description = "区域类别说明")
    @ExcelProperty("区域类别说明")
    private String regionCategoryDesc;

    @Schema(description = "门牌楼牌间隔(米)")
    @ExcelProperty("门牌楼牌间隔(米)")
    private BigDecimal houseInterval;

    @Schema(description = "兴趣点间隔(米)")
    @ExcelProperty("兴趣点间隔(米)")
    private BigDecimal poiInterval;

    @Schema(description = "特殊兴趣点采集要求")
    @ExcelProperty("特殊兴趣点采集要求")
    private String specialPoiRequirement;

    @Schema(description = "启用状态(0-禁用,1-启用)", example = "2")
    @ExcelProperty("启用状态(0-禁用,1-启用)")
    private Integer enableStatus;

    @Schema(description = "配置人")
    @ExcelProperty("配置人")
    private String configUser;

    @Schema(description = "配置时间")
    @ExcelProperty("配置时间")
    private LocalDateTime configTime;

    @Schema(description = "备注", example = "你猜")
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
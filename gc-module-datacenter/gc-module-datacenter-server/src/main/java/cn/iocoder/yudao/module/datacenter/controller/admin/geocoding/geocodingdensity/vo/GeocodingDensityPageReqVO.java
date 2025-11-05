package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingdensity.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 数据采集密度配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeocodingDensityPageReqVO extends PageParam {

    @Schema(description = "密度配置ID", example = "13375")
    private String densityConfigId;

    @Schema(description = "区域类别")
    private String regionCategory;

    @Schema(description = "区域类别说明")
    private String regionCategoryDesc;

    @Schema(description = "门牌楼牌间隔(米)")
    private BigDecimal houseInterval;

    @Schema(description = "兴趣点间隔(米)")
    private BigDecimal poiInterval;

    @Schema(description = "特殊兴趣点采集要求")
    private String specialPoiRequirement;

    @Schema(description = "启用状态(0-禁用,1-启用)", example = "2")
    private Integer enableStatus;

    @Schema(description = "配置人")
    private String configUser;

    @Schema(description = "配置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] configTime;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    private String extCategory3;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
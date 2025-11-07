package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingaccuracy.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 位置精度标准配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeocodingAccuracyPageReqVO extends PageParam {

    @Schema(description = "精度配置ID", example = "27116")
    private String accuracyConfigId;

    @Schema(description = "地形类型", example = "1")
    private String terrainType;

    @Schema(description = "点位中误差标准(米)")
    private BigDecimal positionErrorStd;

    @Schema(description = "最大限差(米)")
    private BigDecimal maxTolerance;

    @Schema(description = "适用数据类型", example = "1")
    private String applicableDataType;

    @Schema(description = "比例尺说明")
    private String scaleDesc;

    @Schema(description = "启用状态(0-禁用,1-启用)", example = "1")
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
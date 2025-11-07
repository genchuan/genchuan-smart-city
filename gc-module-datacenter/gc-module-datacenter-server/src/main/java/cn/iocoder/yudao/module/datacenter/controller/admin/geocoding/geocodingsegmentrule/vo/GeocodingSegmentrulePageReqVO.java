package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingsegmentrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 地理编码分段组合规则配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeocodingSegmentrulePageReqVO extends PageParam {

    @Schema(description = "规则ID")
    private String segmentRuleId;

    @Schema(description = "定位对象类型")
    private String locationType;

    @Schema(description = "分段规则")
    private String segmentRule;

    @Schema(description = "组合规则")
    private String combineRule;

    @Schema(description = "方位描述选项")
    private String directionOptions;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    private Integer enableStatus;

    @Schema(description = "配置人")
    private String configUser;

    @Schema(description = "配置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] configTime;

    @Schema(description = "备注")
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
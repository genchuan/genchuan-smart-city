package cn.iocoder.yudao.module.envir.controller.admin.river.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 河道分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RiverPageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "16738")
    private String riverId;

    @Schema(description = "河道名称", example = "王五")
    private String name;

    @Schema(description = "责任河段（如：XX河上游0-5km/中游5-10km/下游10-15km）")
    private String responsibilitySection;

    @Schema(description = "所属区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "水域保洁频次（可选值：每2小时/每日3次/每日2次/每日1次/每周2次/汛期加密）")
    private String waterCleaningFrequency;

    @Schema(description = "陆域清扫频次（可选值：每小时/每日2次/每日1次/隔日1次/每周1次）")
    private String landCleaningFrequency;

    @Schema(description = "水质监测周期（单位：天）")
    private Integer waterQualityCycle;

    @Schema(description = "水质监测详细数据（含透明度、异味等级、污染物浓度<氨氮/COD/总磷>等）")
    private String waterQualityData;

    @Schema(description = "污染溯源ID（关联溯源表）", example = "1146")
    private String pollutionSourceId;

    @Schema(description = "负责人（关联sys_user.id）", example = "24325")
    private String managerId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalUpdateTime;

    @Schema(description = "垃圾打捞量（单位：吨/日）")
    private BigDecimal wasteFishingVolume;

    @Schema(description = "保洁覆盖率（0.00-100.00）")
    private BigDecimal cleaningCoverage;

    @Schema(description = "水质达标率（0.00-100.00）")
    private BigDecimal waterQualityRate;

    @Schema(description = "垃圾打捞对比照片URL（多个用逗号分隔）", example = "https://www.iocoder.cn")
    private String fishingPhotoUrl;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
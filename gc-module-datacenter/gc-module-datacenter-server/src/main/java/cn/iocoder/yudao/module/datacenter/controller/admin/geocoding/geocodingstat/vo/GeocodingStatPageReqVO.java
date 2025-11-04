package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 基本地点数据统计报表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeocodingStatPageReqVO extends PageParam {

    @Schema(description = "统计ID")
    private String statId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计时间")
    private LocalDateTime statTime;

    @Schema(description = "行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称")
    private String adminName;

    @Schema(description = "区域数据数量")
    private Integer regionCount;

    @Schema(description = "地片与区片数据数量")
    private Integer zoneCount;

    @Schema(description = "街巷数据数量")
    private Integer streetCount;

    @Schema(description = "门牌楼牌数据数量")
    private Integer houseCount;

    @Schema(description = "兴趣点数据数量")
    private Integer poiCount;

    @Schema(description = "新增数据数量")
    private Integer newDataCount;

    @Schema(description = "更新数据数量")
    private Integer updateDataCount;

    @Schema(description = "统计人")
    private String statUser;

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
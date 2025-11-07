package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstreet.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 街巷数据管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeocodingStreetPageReqVO extends PageParam {

    @Schema(description = "街巷ID")
    private String streetId;

    @Schema(description = "名称")
    private String streetName;

    @Schema(description = "标识码")
    private String streetCode;

    @Schema(description = "别名")
    private String alias;

    @Schema(description = "曾用名")
    private String oldName;

    @Schema(description = "最小门牌号")
    private String minHouseNum;

    @Schema(description = "最大门牌号")
    private String maxHouseNum;

    @Schema(description = "起点名称")
    private String beginningName;

    @Schema(description = "止点名称")
    private String endingName;

    @Schema(description = "走向")
    private String direction;

    @Schema(description = "中心线坐标串")
    private String centerLineCoords;

    @Schema(description = "数据来源")
    private String dataSource;

    @Schema(description = "初始日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] initDate;

    @Schema(description = "变更日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateDate;

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
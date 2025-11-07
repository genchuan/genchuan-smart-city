package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinghouse.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 门(楼)牌数据管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeocodingHousePageReqVO extends PageParam {

    @Schema(description = "门牌楼牌ID")
    private String houseId;

    @Schema(description = "名称")
    private String houseName;

    @Schema(description = "标识码")
    private String houseCode;

    @Schema(description = "北斗网格位置码")
    private String bdGridCode;

    @Schema(description = "所在街巷")
    private String streetName;

    @Schema(description = "所在地片/区片")
    private String zoneName;

    @Schema(description = "门(楼)牌号")
    private String houseNum;

    @Schema(description = "所在街道名称")
    private String parentStreetName;

    @Schema(description = "所在街道代码")
    private String streetCode;

    @Schema(description = "所在社区名称")
    private String communityName;

    @Schema(description = "所在社区代码")
    private String communityCode;

    @Schema(description = "位置点坐标X")
    private BigDecimal coordX;

    @Schema(description = "位置点坐标Y")
    private BigDecimal coordY;

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
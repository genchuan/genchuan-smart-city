package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectrouteinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检路线信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectRouteInfoPageReqVO extends PageParam {

    @Schema(description = "路线ID")
    private String routeId;

    @Schema(description = "路线名称")
    private String routeName;

    @Schema(description = "路线编码")
    private String routeCode;

    @Schema(description = "所属行政区划代码")
    private String areaCode;

    @Schema(description = "所属行政区划名称")
    private String areaName;

    @Schema(description = "起点名称")
    private String startPtName;

    @Schema(description = "起点坐标X")
    private BigDecimal startPtX;

    @Schema(description = "起点坐标Y")
    private BigDecimal startPtY;

    @Schema(description = "终点名称")
    private String endPtName;

    @Schema(description = "终点坐标X")
    private BigDecimal endPtX;

    @Schema(description = "终点坐标Y")
    private BigDecimal endPtY;

    @Schema(description = "途经网格IDs")
    private String passGridIds;

    @Schema(description = "途经网格名称")
    private String passGridNames;

    @Schema(description = "预计时长")
    private Integer estimateEndure;

    @Schema(description = "巡查频次")
    private String inspectFrequency;

    @Schema(description = "负责人员ID")
    private String personId;

    @Schema(description = "负责人员姓名")
    private String personName;

    @Schema(description = "路线状态")
    private Boolean routeStatus;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间（业务）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "更新时间（业务）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizUpdateTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
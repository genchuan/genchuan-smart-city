package cn.iocoder.yudao.module.datacenter.controller.admin.patrolroute.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查路线分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PatrolRoutePageReqVO extends PageParam {

    @Schema(description = "路线名称")
    private String routeName;

    @Schema(description = "路线编码")
    private String routeCode;

    @Schema(description = "所属区域ID")
    private String areaId;

    @Schema(description = "所属区域名称")
    private String areaName;

    @Schema(description = "路线类型")
    private String routeType;

    @Schema(description = "关联点位IDs")
    private String relatedPointIds;

    @Schema(description = "点位名称列表")
    private String pointNameList;

    @Schema(description = "路线长度")
    private Double routeLength;

    @Schema(description = "预计耗时(分钟)")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] estimatedTime;

    @Schema(description = "路线描述")
    private String routeDescription;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    private Boolean enabledStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
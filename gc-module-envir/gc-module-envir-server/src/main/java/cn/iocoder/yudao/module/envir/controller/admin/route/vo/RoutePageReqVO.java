package cn.iocoder.yudao.module.envir.controller.admin.route.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 路线分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoutePageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "28138")
    private String sysRouteId;

    @Schema(description = "路线名称", example = "王五")
    private String name;

    @Schema(description = "路线编码")
    private String code;

    @Schema(description = "起点（含经度、纬度、具体地址）")
    private String startPoint;

    @Schema(description = "终点（含经度、纬度、具体地址）")
    private String endPoint;

    @Schema(description = "路线经纬度坐标（数组格式：[{\"lng\":116.40,\"lat\":39.91},{\"lng\":116.41,\"lat\":39.92}]）")
    private String routePoints;

    @Schema(description = "路线长度（单位：公里）")
    private BigDecimal length;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "2")
    private Integer status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

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
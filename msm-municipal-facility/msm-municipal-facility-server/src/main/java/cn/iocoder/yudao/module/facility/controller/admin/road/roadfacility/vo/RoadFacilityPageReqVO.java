package cn.iocoder.yudao.module.facility.controller.admin.road.roadfacility.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 道路设施分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoadFacilityPageReqVO extends PageParam {

    @Schema(description = "[道路编码] UUID格式")
    private String roadCode;

    @Schema(description = "[路段名称] 路段名称", example = "李四")
    private String roadName;

    @Schema(description = "[所属区域编码] 12位地区码（GB/T 2260），关联sys_area.full_code")
    private String areaCode;

    @Schema(description = "[所在地区名称]", example = "张三")
    private String areaName;

    @Schema(description = "[路段长度] 路段长度，数值")
    private BigDecimal length;

    @Schema(description = "[路段宽度] 路段宽度，数值")
    private BigDecimal width;

    @Schema(description = "[建成时间] 建成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] buildTime;

    @Schema(description = "[使用状态] 如:正常/维修中/废弃", example = "1")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}

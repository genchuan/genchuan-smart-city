package cn.iocoder.yudao.module.waterdetection.controller.admin.watersourcemanagement.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 水源类型及属性管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WaterSourceManagementPageReqVO extends PageParam {

    @Schema(description = "水源编码")
    private String sourceCode;

    @Schema(description = "水源名称")
    private String sourceName;

    @Schema(description = "水源类型")
    private String sourceType;

    @Schema(description = "经度")
    private Double longitude;

    @Schema(description = "纬度")
    private Double latitude;

    @Schema(description = "所属行政区")
    private String administrativeRegion;

    @Schema(description = "水源描述")
    private String sourceDescription;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
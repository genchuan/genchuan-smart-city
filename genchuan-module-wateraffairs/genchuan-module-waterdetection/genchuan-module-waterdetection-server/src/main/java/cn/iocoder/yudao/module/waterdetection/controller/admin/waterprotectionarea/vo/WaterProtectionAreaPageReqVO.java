package cn.iocoder.yudao.module.waterdetection.controller.admin.waterprotectionarea.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 水源保护区管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WaterProtectionAreaPageReqVO extends PageParam {

    @Schema(description = "保护区级别")
    private String protectionLevel;

    @Schema(description = "边界经纬度范围")
    private String boundaryRange;

    @Schema(description = "标识牌编号")
    private String signboardNo;

    @Schema(description = "标识牌位置")
    private String signboardLocation;

    @Schema(description = "安装时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] installTime;

    @Schema(description = "维护记录")
    private String maintenanceRecord;

    @Schema(description = "污染源治理状态", example = "2")
    private String pollutionStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
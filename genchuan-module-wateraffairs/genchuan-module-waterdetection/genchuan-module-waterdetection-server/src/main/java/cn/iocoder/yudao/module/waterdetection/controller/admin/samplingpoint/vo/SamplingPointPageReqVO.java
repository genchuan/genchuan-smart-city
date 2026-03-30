package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingpoint.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 采样点规划分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SamplingPointPageReqVO extends PageParam {

    @Schema(description = "采样点编号")
    private String pointCode;

    @Schema(description = "经度")
    private Double longitude;

    @Schema(description = "纬度")
    private Double latitude;

    @Schema(description = "类型(水源/水厂/管网/末梢)")
    private String pointType;

    @Schema(description = "覆盖人口")
    private Double coveredPopulation;

    @Schema(description = "周边环境描述")
    private String surroundingDesc;

    @Schema(description = "规划依据")
    private String planningBasis;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
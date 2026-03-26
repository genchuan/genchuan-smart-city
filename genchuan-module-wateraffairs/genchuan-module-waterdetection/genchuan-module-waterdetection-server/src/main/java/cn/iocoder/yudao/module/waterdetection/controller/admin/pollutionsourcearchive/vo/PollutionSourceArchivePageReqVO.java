package cn.iocoder.yudao.module.waterdetection.controller.admin.pollutionsourcearchive.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 周边污染源档案管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PollutionSourceArchivePageReqVO extends PageParam {

    @Schema(description = "污染源编号")
    private String pollutionNo;

    @Schema(description = "污染源类型")
    private String pollutionType;

    @Schema(description = "经度")
    private Double longitude;

    @Schema(description = "纬度")
    private Double latitude;

    @Schema(description = "污染程度")
    private String pollutionLevel;

    @Schema(description = "治理措施")
    private String treatmentMeasures;

    @Schema(description = "治理状态")
    private String treatmentStatus;

    @Schema(description = "排查时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inspectionTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
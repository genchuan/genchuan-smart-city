package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 外检统计水质检测结果汇总分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WaterSampleTestSummaryPageReqVO extends PageParam {

    @Schema(description = "排序字段", example = "id")
    private String sortField;

    @Schema(description = "排序方式", example = "DESC", allowableValues = {"ASC", "DESC"})
    private String sortOrder;

    @Schema(description = "委托单位")
    private String clientName;

    @Schema(description = "收样日期")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String receiveDate;

    @Schema(description = "样品编号")
    private String sampleNo;

    @Schema(description = "样品名称")
    private String sampleName;

    @Schema(description = "采样地点")
    private String samplingLocation;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

}
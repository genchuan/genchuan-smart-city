package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampleinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 水质检测信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WaterSampleInfoPageReqVO extends PageParam {

    @Schema(description = "样品编号")
    private String sampleNo;

    @Schema(description = "样品类型")
    private String sampleType;

    @Schema(description = "样品名称")
    private String sampleName;

    @Schema(description = "检测性质")
    private String sampleNature;

    @Schema(description = "样品状态")
    private String sampleStatus;

    @Schema(description = "采样方式")
    private String deliveryMethod;

    @Schema(description = "采样时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] samplingDate;

    @Schema(description = "采样地点")
    private String samplingLocation;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "检测开始日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startDate;

    @Schema(description = "检测结束日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endDate;

}
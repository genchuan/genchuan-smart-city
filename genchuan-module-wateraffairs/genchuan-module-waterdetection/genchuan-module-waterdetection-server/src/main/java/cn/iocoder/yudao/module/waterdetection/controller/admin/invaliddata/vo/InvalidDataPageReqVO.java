package cn.iocoder.yudao.module.waterdetection.controller.admin.invaliddata.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 不合格数据处理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvalidDataPageReqVO extends PageParam {

    @Schema(description = "数据ID")
    private String dataId;

    @Schema(description = "仪器ID")
    private String instrumentId;

    @Schema(description = "监测值")
    private Double monitorValue;

    @Schema(description = "采集时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] collectionTime;

    @Schema(description = "数据状态(有效/无效)")
    private String dataStatus;

    @Schema(description = "无效原因")
    private String invalidReason;

    @Schema(description = "剔除标记(0未剔除1已剔除)")
    private Boolean isExcluded;

    @Schema(description = "处理人员ID")
    private String processorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
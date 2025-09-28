package cn.iocoder.yudao.module.gc.controller.admin.alarminformation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlarmInformationPageReqVO extends PageParam {

    @Schema(description = "预警编号")
    private String alarmCode;

    @Schema(description = "风险类型ID", example = "17761")
    private String riskTypeId;

    @Schema(description = "风险类型名称", example = "张三")
    private String riskTypeName;

    @Schema(description = "预警等级")
    private String alarmLevel;

    @Schema(description = "所属分域ID", example = "23037")
    private String domainId;

    @Schema(description = "所属分域名称", example = "赵六")
    private String domainName;

    @Schema(description = "发生区域")
    private String occurRegion;

    @Schema(description = "GPS坐标")
    private String gpsCoordinate;

    @Schema(description = "触发时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] triggerTime;

    @Schema(description = "预警状态", example = "1")
    private String alarmStatus;

    @Schema(description = "触发原因", example = "不好")
    private String triggerReason;

    @Schema(description = "关联指标ID", example = "13170")
    private String indicatorId;

    @Schema(description = "处置责任人ID", example = "30406")
    private String handlerId;

    @Schema(description = "处置责任人姓名", example = "赵六")
    private String handlerName;

    @Schema(description = "最后更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastUpdateTime;

}
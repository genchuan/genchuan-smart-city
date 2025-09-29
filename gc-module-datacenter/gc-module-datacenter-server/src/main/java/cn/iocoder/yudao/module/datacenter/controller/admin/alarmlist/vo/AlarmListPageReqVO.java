package cn.iocoder.yudao.module.datacenter.controller.admin.alarmlist.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警告警列分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlarmListPageReqVO extends PageParam {

    @Schema(description = "预警ID（UUID）", example = "30266")
    private String alarmId;

    @Schema(description = "预警编号（AL+分域编码+8位流水号）")
    private String alarmCode;

    @Schema(description = "风险类型ID", example = "10366")
    private String riskTypeId;

    @Schema(description = "风险类型名称", example = "赵六")
    private String riskTypeName;

    @Schema(description = "预警等级（一般/较大/重大/特别重大）")
    private String alarmLevel;

    @Schema(description = "所属分域ID", example = "3752")
    private String domainId;

    @Schema(description = "所属分域名称", example = "赵六")
    private String domainName;

    @Schema(description = "GPS坐标（经度,纬度）")
    private String gpsCoordinate;

    @Schema(description = "预警状态（待处置/处置中/已完成/已解除）", example = "1")
    private String alarmStatus;

    @Schema(description = "触发原因", example = "不香")
    private String triggerReason;

    @Schema(description = "关联指标ID", example = "9591")
    private String indicatorId;

    @Schema(description = "处置责任人ID", example = "24403")
    private String handlerId;

    @Schema(description = "处置责任人姓名", example = "芋艿")
    private String handlerName;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
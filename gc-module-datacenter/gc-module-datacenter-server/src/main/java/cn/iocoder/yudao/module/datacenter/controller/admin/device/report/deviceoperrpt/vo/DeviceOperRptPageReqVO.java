package cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备运行状态统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceOperRptPageReqVO extends PageParam {

    @Schema(description = "统计ID")
    private String operStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称")
    private String regionName;

    @Schema(description = "设备大类ID")
    private String deviceMajorId;

    @Schema(description = "设备大类名称")
    private String deviceMajorName;

    @Schema(description = "设备总数量(台)")
    private Integer totalDeviceCount;

    @Schema(description = "平均在线设备数(台)")
    private Integer avgOnlineCount;

    @Schema(description = "设备在线率(%)")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "总离线时长(分钟)")
    private Integer totalOfflineEndure;

    @Schema(description = "平均离线时长(分钟)")
    private BigDecimal avgOfflineEndure;

    @Schema(description = "一般预警数(条)")
    private Integer generalAlertCount;

    @Schema(description = "重要预警数(条)")
    private Integer importantAlertCount;

    @Schema(description = "紧急预警数(条)")
    private Integer urgentAlertCount;

    @Schema(description = "已处理预警数(条)")
    private Integer handledAlertCount;

    @Schema(description = "预警处置率(%)")
    private BigDecimal alertHandleRate;

    @Schema(description = "统计时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

    @Schema(description = "统计人")
    private String statUser;

    @Schema(description = "报表备注")
    private String rptRemark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
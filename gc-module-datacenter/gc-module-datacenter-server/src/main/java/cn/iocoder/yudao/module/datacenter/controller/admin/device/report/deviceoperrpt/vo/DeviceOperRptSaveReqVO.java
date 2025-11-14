package cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备运行状态统计新增/修改 Request VO")
@Data
public class DeviceOperRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID")
    private String operStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划名称不能为空")
    private String regionName;

    @Schema(description = "设备大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备大类ID不能为空")
    private String deviceMajorId;

    @Schema(description = "设备大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备大类名称不能为空")
    private String deviceMajorName;

    @Schema(description = "设备总数量(台)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "设备总数量(台)不能为空")
    private Integer totalDeviceCount;

    @Schema(description = "平均在线设备数(台)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "平均在线设备数(台)不能为空")
    private Integer avgOnlineCount;

    @Schema(description = "设备在线率(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "设备在线率(%)不能为空")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "总离线时长(分钟)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总离线时长(分钟)不能为空")
    private Integer totalOfflineEndure;

    @Schema(description = "平均离线时长(分钟)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "平均离线时长(分钟)不能为空")
    private BigDecimal avgOfflineEndure;

    @Schema(description = "一般预警数(条)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "一般预警数(条)不能为空")
    private Integer generalAlertCount;

    @Schema(description = "重要预警数(条)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "重要预警数(条)不能为空")
    private Integer importantAlertCount;

    @Schema(description = "紧急预警数(条)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "紧急预警数(条)不能为空")
    private Integer urgentAlertCount;

    @Schema(description = "已处理预警数(条)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "已处理预警数(条)不能为空")
    private Integer handledAlertCount;

    @Schema(description = "预警处置率(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警处置率(%)不能为空")
    private BigDecimal alertHandleRate;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计人不能为空")
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

}
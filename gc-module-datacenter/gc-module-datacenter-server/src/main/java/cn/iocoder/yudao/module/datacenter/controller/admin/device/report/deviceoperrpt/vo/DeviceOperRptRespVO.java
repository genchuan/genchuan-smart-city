package cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备运行状态统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceOperRptRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "统计ID")
    @ExcelProperty("统计ID")
    private String operStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划名称")
    private String regionName;

    @Schema(description = "设备大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备大类ID")
    private String deviceMajorId;

    @Schema(description = "设备大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备大类名称")
    private String deviceMajorName;

    @Schema(description = "设备总数量(台)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备总数量(台)")
    private Integer totalDeviceCount;

    @Schema(description = "平均在线设备数(台)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("平均在线设备数(台)")
    private Integer avgOnlineCount;

    @Schema(description = "设备在线率(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备在线率(%)")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "总离线时长(分钟)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("总离线时长(分钟)")
    private Integer totalOfflineEndure;

    @Schema(description = "平均离线时长(分钟)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("平均离线时长(分钟)")
    private BigDecimal avgOfflineEndure;

    @Schema(description = "一般预警数(条)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("一般预警数(条)")
    private Integer generalAlertCount;

    @Schema(description = "重要预警数(条)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("重要预警数(条)")
    private Integer importantAlertCount;

    @Schema(description = "紧急预警数(条)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("紧急预警数(条)")
    private Integer urgentAlertCount;

    @Schema(description = "已处理预警数(条)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("已处理预警数(条)")
    private Integer handledAlertCount;

    @Schema(description = "预警处置率(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警处置率(%)")
    private BigDecimal alertHandleRate;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间")
    private LocalDateTime statTime;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计人")
    private String statUser;

    @Schema(description = "报表备注")
    @ExcelProperty("报表备注")
    private String rptRemark;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
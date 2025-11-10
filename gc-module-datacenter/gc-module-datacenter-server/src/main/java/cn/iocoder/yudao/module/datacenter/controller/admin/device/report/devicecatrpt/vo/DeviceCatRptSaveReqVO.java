package cn.iocoder.yudao.module.datacenter.controller.admin.device.report.devicecatrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备分类统计新增/修改 Request VO")
@Data
public class DeviceCatRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID")
    private String catStatId;

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

    @Schema(description = "设备中类ID")
    private String deviceMidId;

    @Schema(description = "设备中类名称")
    private String deviceMidName;

    @Schema(description = "设备小类ID")
    private String deviceMinorId;

    @Schema(description = "设备小类名称")
    private String deviceMinorName;

    @Schema(description = "设备总数(台)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "设备总数(台)不能为空")
    private Integer totalDeviceCount;

    @Schema(description = "在线设备数(台)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "在线设备数(台)不能为空")
    private Integer onlineDeviceCount;

    @Schema(description = "离线设备数(台)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "离线设备数(台)不能为空")
    private Integer offlineDeviceCount;

    @Schema(description = "故障设备数(台)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "故障设备数(台)不能为空")
    private Integer faultDeviceCount;

    @Schema(description = "新增设备数(台)")
    private Integer newDeviceCount;

    @Schema(description = "报废设备数(台)")
    private Integer scrappedDeviceCount;

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
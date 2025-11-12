package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domdevicemonrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备分域监测报表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DomDeviceMonRptRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "报表ID")
    @ExcelProperty("报表ID")
    private String rptId;

    @Schema(description = "统计周期")
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称")
    @ExcelProperty("统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码")
    @ExcelProperty("行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称")
    @ExcelProperty("行政区划名称")
    private String adminName;

    @Schema(description = "设备大类ID")
    @ExcelProperty("设备大类ID")
    private String deviceMajorId;

    @Schema(description = "设备大类名称")
    @ExcelProperty("设备大类名称")
    private String deviceMajorName;

    @Schema(description = "设备小类ID")
    @ExcelProperty("设备小类ID")
    private String deviceMinorId;

    @Schema(description = "设备小类名称")
    @ExcelProperty("设备小类名称")
    private String deviceMinorName;

    @Schema(description = "分域ID")
    @ExcelProperty("分域ID")
    private String domId;

    @Schema(description = "分域名称")
    @ExcelProperty("分域名称")
    private String domName;

    @Schema(description = "设备总数")
    @ExcelProperty("设备总数")
    private Integer totalDeviceCount;

    @Schema(description = "设备在线率")
    @ExcelProperty("设备在线率")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "数据上传总频次")
    @ExcelProperty("数据上传总频次")
    private Integer totalUploadFreq;

    @Schema(description = "数据上传达标率")
    @ExcelProperty("数据上传达标率")
    private BigDecimal uploadQualifiedRate;

    @Schema(description = "设备异常次数")
    @ExcelProperty("设备异常次数")
    private Integer deviceAbnCount;

    @Schema(description = "固件更新设备数")
    @ExcelProperty("固件更新设备数")
    private Integer firmwareUpdatedCount;

    @Schema(description = "固件更新率")
    @ExcelProperty("固件更新率")
    private BigDecimal firmwareUpdateRate;

    @Schema(description = "报表生成时间")
    @ExcelProperty("报表生成时间")
    private LocalDateTime rptCreateTime;

    @Schema(description = "报表生成人")
    @ExcelProperty("报表生成人")
    private Long rptCreateUser;

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
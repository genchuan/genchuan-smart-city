package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommonevtmonrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测事件分域监测报表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DomMonEvtMonRptRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "报表ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表ID")
    private String rptId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划名称")
    private String adminName;

    @Schema(description = "监测事件大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测事件大类ID")
    private String monEvtMajorId;

    @Schema(description = "监测事件大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测事件大类名称")
    private String monEvtMajorName;

    @Schema(description = "监测事件小类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测事件小类ID")
    private String monEvtMinorId;

    @Schema(description = "监测事件小类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测事件小类名称")
    private String monEvtMinorName;

    @Schema(description = "分域ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分域ID")
    private String domId;

    @Schema(description = "分域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分域名称")
    private String domName;

    @Schema(description = "事件总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件总数")
    private Integer totalEvtCount;

    @Schema(description = "高发区域代码")
    @ExcelProperty("高发区域代码")
    private String highIncidenceCode;

    @Schema(description = "高发区域名称")
    @ExcelProperty("高发区域名称")
    private String highIncidenceName;

    @Schema(description = "高发区域事件占比")
    @ExcelProperty("高发区域事件占比")
    private BigDecimal highIncidenceRatio;

    @Schema(description = "事件办结率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件办结率")
    private BigDecimal evtCompleteRate;

    @Schema(description = "平均响应时长")
    @ExcelProperty("平均响应时长")
    private BigDecimal avgRespEndure;

    @Schema(description = "报表生成时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表生成时间")
    private LocalDateTime rptCreateTime;

    @Schema(description = "报表生成人", requiredMode = Schema.RequiredMode.REQUIRED)
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
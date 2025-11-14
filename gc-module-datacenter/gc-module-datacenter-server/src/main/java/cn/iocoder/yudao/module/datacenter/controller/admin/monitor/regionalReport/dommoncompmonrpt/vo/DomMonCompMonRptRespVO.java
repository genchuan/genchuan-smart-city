package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommoncompmonrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测部件分域监测报表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DomMonCompMonRptRespVO {

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

    @Schema(description = "监测部件大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测部件大类ID")
    private String monCompMajorId;

    @Schema(description = "监测部件大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测部件大类名称")
    private String monCompMajorName;

    @Schema(description = "监测部件小类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测部件小类ID")
    private String monCompMinorId;

    @Schema(description = "监测部件小类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测部件小类名称")
    private String monCompMinorName;

    @Schema(description = "分域ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分域ID")
    private String domId;

    @Schema(description = "分域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分域名称")
    private String domName;

    @Schema(description = "设备总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备总数")
    private Integer totalDeviceCount;

    @Schema(description = "设备在线率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备在线率")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "数据上传总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据上传总数")
    private Integer totalUploadCount;

    @Schema(description = "数据上传达标率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据上传达标率")
    private BigDecimal uploadQualifiedRate;

    @Schema(description = "故障事件数")
    @ExcelProperty("故障事件数")
    private Integer faultEvtCount;

    @Schema(description = "校准次数")
    @ExcelProperty("校准次数")
    private Integer calibrateCount;

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
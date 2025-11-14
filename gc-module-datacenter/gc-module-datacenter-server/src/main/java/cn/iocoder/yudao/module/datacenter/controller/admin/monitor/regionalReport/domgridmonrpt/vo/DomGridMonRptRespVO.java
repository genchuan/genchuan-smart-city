package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domgridmonrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 网格分域监测报表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DomGridMonRptRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
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

    @Schema(description = "网格类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网格类型")
    private String gridType;

    @Schema(description = "分域ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分域ID")
    private String domId;

    @Schema(description = "分域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分域名称")
    private String domName;

    @Schema(description = "网格编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网格编码")
    private String gridCode;

    @Schema(description = "网格员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网格员姓名")
    private String gridUserName;

    @Schema(description = "监测点位数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测点位数量")
    private Integer monPtCount;

    @Schema(description = "设备在线率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备在线率")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "事件总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件总数")
    private Integer evtTotalCount;

    @Schema(description = "事件办结率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件办结率")
    private BigDecimal evtCompleteRate;

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
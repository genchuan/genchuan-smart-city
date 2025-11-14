package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domappscenemonrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 应用场景分域监测报表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DomAppSceneMonRptRespVO {

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

    @Schema(description = "应用场景大类ID")
    @ExcelProperty("应用场景大类ID")
    private String appSceneMajorId;

    @Schema(description = "应用场景大类名称")
    @ExcelProperty("应用场景大类名称")
    private String appSceneMajorName;

    @Schema(description = "应用场景中类ID")
    @ExcelProperty("应用场景中类ID")
    private String appSceneMidId;

    @Schema(description = "应用场景中类名称")
    @ExcelProperty("应用场景中类名称")
    private String appSceneMidName;

    @Schema(description = "应用场景小类ID")
    @ExcelProperty("应用场景小类ID")
    private String appSceneMinorId;

    @Schema(description = "应用场景小类名称")
    @ExcelProperty("应用场景小类名称")
    private String appSceneMinorName;

    @Schema(description = "分域ID")
    @ExcelProperty("分域ID")
    private String domId;

    @Schema(description = "分域名称")
    @ExcelProperty("分域名称")
    private String domName;

    @Schema(description = "关联设备总数")
    @ExcelProperty("关联设备总数")
    private Integer totalDeviceCount;

    @Schema(description = "设备在线率")
    @ExcelProperty("设备在线率")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "场景事件总数")
    @ExcelProperty("场景事件总数")
    private Integer sceneEvtCount;

    @Schema(description = "事件处置率")
    @ExcelProperty("事件处置率")
    private BigDecimal evtHandleRate;

    @Schema(description = "资源调度次数")
    @ExcelProperty("资源调度次数")
    private Integer resDispatchCount;

    @Schema(description = "场景健康度")
    @ExcelProperty("场景健康度")
    private BigDecimal sceneHealthRate;

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
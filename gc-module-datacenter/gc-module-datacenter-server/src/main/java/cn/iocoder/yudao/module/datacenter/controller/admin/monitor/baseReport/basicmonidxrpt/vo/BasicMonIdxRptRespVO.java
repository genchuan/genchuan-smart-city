package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonidxrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 基础监测指标报 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BasicMonIdxRptRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "报表ID")
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

    @Schema(description = "点位类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("点位类型")
    private String ptType;

    @Schema(description = "点位总数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("点位总数(个)")
    private Integer totalPtCount;

    @Schema(description = "启用点位数量(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("启用点位数量(个)")
    private Integer enablePtCount;

    @Schema(description = "停用点位数量(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("停用点位数量(个)")
    private Integer disablePtCount;

    @Schema(description = "新增点位数量(个)")
    @ExcelProperty("新增点位数量(个)")
    private Integer newPtCount;

    @Schema(description = "关联设备总数(个)")
    @ExcelProperty("关联设备总数(个)")
    private Integer relDeviceCount;

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
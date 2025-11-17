package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectrouteinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检路线信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectRouteInfoRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "路线ID")
    @ExcelProperty("路线ID")
    private String routeId;

    @Schema(description = "路线名称")
    @ExcelProperty("路线名称")
    private String routeName;

    @Schema(description = "路线编码")
    @ExcelProperty("路线编码")
    private String routeCode;

    @Schema(description = "所属行政区划代码")
    @ExcelProperty("所属行政区划代码")
    private String areaCode;

    @Schema(description = "所属行政区划名称")
    @ExcelProperty("所属行政区划名称")
    private String areaName;

    @Schema(description = "起点名称")
    @ExcelProperty("起点名称")
    private String startPtName;

    @Schema(description = "起点坐标X")
    @ExcelProperty("起点坐标X")
    private BigDecimal startPtX;

    @Schema(description = "起点坐标Y")
    @ExcelProperty("起点坐标Y")
    private BigDecimal startPtY;

    @Schema(description = "终点名称")
    @ExcelProperty("终点名称")
    private String endPtName;

    @Schema(description = "终点坐标X")
    @ExcelProperty("终点坐标X")
    private BigDecimal endPtX;

    @Schema(description = "终点坐标Y")
    @ExcelProperty("终点坐标Y")
    private BigDecimal endPtY;

    @Schema(description = "途经网格IDs")
    @ExcelProperty("途经网格IDs")
    private String passGridIds;

    @Schema(description = "途经网格名称")
    @ExcelProperty("途经网格名称")
    private String passGridNames;

    @Schema(description = "预计时长")
    @ExcelProperty("预计时长")
    private Integer estimateEndure;

    @Schema(description = "巡查频次")
    @ExcelProperty("巡查频次")
    private String inspectFrequency;

    @Schema(description = "负责人员ID")
    @ExcelProperty("负责人员ID")
    private String personId;

    @Schema(description = "负责人员姓名")
    @ExcelProperty("负责人员姓名")
    private String personName;

    @Schema(description = "路线状态")
    @ExcelProperty("路线状态")
    private Boolean routeStatus;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createUser;

    @Schema(description = "创建时间（业务）")
    @ExcelProperty("创建时间（业务）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateUser;

    @Schema(description = "更新时间（业务）")
    @ExcelProperty("更新时间（业务）")
    private LocalDateTime bizUpdateTime;

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
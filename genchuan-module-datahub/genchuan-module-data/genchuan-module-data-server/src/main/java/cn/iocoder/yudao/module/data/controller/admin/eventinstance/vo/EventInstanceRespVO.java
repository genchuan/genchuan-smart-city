package cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测事件实例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EventInstanceRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "事件名称")
    @ExcelProperty("事件名称")
    private String name;

    @Schema(description = "18位标识码")
    @ExcelProperty("18位标识码")
    private String uniqueCode;

    @Schema(description = "所属分类ID")
    @ExcelProperty("所属分类ID")
    private String categoryId;

    @Schema(description = "所属分类")
    @ExcelProperty("所属分类")
    private String categoryName;

    @Schema(description = "关联监测实例ID")
    @ExcelProperty("关联监测实例ID")
    private String monitorId;

    @Schema(description = "监测实例名称")
    @ExcelProperty("监测实例名称")
    private String monitorName;

    @Schema(description = "坐标信息")
    @ExcelProperty("坐标信息")
    private String coordinate;

    @Schema(description = "事件等级")
    @ExcelProperty("事件等级")
    private String eventLevel;

    @Schema(description = "描述信息")
    @ExcelProperty("描述信息")
    private String description;

    @Schema(description = "状态")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "行政区划代码")
    @ExcelProperty("行政区划代码")
    private String areaCode;

    @Schema(description = "行政区划归属")
    @ExcelProperty("行政区划归属")
    private String areaName;

    @Schema(description = "关联管理事项ID")
    @ExcelProperty("关联管理事项ID")
    private String matterId;

    @Schema(description = "关联管理事项")
    @ExcelProperty("关联管理事项")
    private String matterName;

    @Schema(description = "预警方式")
    @ExcelProperty("预警方式")
    private String warningWay;

    @Schema(description = "上报来源")
    @ExcelProperty("上报来源")
    private String reportSource;

    @Schema(description = "处置日志")
    @ExcelProperty("处置日志")
    private String disposeLog;

    @Schema(description = "处置人")
    @ExcelProperty("处置人")
    private String handler;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime dealTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
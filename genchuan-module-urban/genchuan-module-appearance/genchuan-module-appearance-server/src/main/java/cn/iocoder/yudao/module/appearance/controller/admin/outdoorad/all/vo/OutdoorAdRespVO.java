package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.all.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
// import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 户外广告 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OutdoorAdRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19511")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "广告ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24013")
    @ExcelProperty("广告ID")
    private String outdoorAdId;

    @Schema(description = "广告名称", example = "芋艿")
    @ExcelProperty("广告名称")
    private String name;

    @Schema(description = "广告位置")
    @ExcelProperty("广告位置")
    private String location;

    @Schema(description = "审批尺寸")
    @ExcelProperty("审批尺寸")
    private String approvedSize;

    @Schema(description = "实际尺寸")
    @ExcelProperty("实际尺寸")
    private String actualSize;

    @Schema(description = "倾斜角度")
    @ExcelProperty("倾斜角度")
    private BigDecimal tiltAngle;

    @Schema(description = "破损状态", example = "10496")
    @ExcelProperty("破损状态")
    private String damageStatusId;

    @Schema(description = "广告状态", example = "27560")
    @ExcelProperty("广告状态")
    private String adStatusId;

    @Schema(description = "所属区域")
    @ExcelProperty("所属区域")
    private String areaCode;

    @Schema(description = "监管员", example = "4257")
    @ExcelProperty("监管员")
    private String supervisorId;

    @Schema(description = "预警类型", example = "23563")
    @ExcelProperty("预警类型")
    private String warningTypeId;

    @Schema(description = "预警时间")
    @ExcelProperty("预警时间")
    private LocalDateTime warningTime;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    // 关联表字段
    @Schema(description = "破损状态名称")
    @ExcelProperty("破损状态名称")
    private String damageStatusName;

    @Schema(description = "广告状态名称")
    @ExcelProperty("广告状态名称")
    private String adStatusName;

    @Schema(description = "区域名称")
    @ExcelProperty("区域名称")
    private String areaName;

    @Schema(description = "监管员名称")
    @ExcelProperty("监管员名称")
    private String supervisorName;

    @Schema(description = "预警类型名称")
    @ExcelProperty("预警类型名称")
    private String warningTypeName;

    @Schema(description = "工单编号")
    @ExcelProperty("工单编号")
    private String orderNo;

    @Schema(description = "工单状态名称")
    @ExcelProperty("工单状态名称")
    private String orderStatusName;

    @Schema(description = "处置人名称")
    @ExcelProperty("处置人名称")
    private String dealByName;

    @Schema(description = "复核结果名称")
    @ExcelProperty("复核结果名称")
    private String reviewResultName;

}
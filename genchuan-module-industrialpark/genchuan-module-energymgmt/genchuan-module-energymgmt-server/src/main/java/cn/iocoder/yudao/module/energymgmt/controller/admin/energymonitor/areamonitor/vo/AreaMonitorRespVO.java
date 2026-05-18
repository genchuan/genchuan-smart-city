package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.areamonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 分区能耗 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AreaMonitorRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12353")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "区域名称", example = "芋艿")
    @ExcelProperty("区域名称")
    private String areaName;

    @Schema(description = "区域面积，单位㎡")
    @ExcelProperty("区域面积，单位㎡")
    private Integer areaSize;

    @Schema(description = "能耗总量")
    @ExcelProperty("能耗总量")
    private BigDecimal totalEnergy;

    @Schema(description = "单位面积能耗")
    @ExcelProperty("单位面积能耗")
    private BigDecimal unitEnergy;

    @Schema(description = "能耗状态：正常能耗/能耗异常", example = "2")
    @ExcelProperty("能耗状态：正常能耗/能耗异常")
    private String energyStatus;

    @Schema(description = "关联设备数", example = "28977")
    @ExcelProperty("关联设备数")
    private Integer deviceCount;

    @Schema(description = "同比变化")
    @ExcelProperty("同比变化")
    private BigDecimal yoyChange;

    @Schema(description = "环比变化")
    @ExcelProperty("环比变化")
    private BigDecimal momChange;

    @Schema(description = "操作人账号")
    @ExcelProperty("操作人账号")
    private String handleUser;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
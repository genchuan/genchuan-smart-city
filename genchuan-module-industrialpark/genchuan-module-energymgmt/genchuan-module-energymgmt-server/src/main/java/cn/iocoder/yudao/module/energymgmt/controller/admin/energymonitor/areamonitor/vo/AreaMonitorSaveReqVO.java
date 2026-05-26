package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.areamonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分区能耗新增/修改 Request VO")
@Data
public class AreaMonitorSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12353")
    private Long id;

    @Schema(description = "区域名称", example = "芋艿")
    private String areaName;

    @Schema(description = "区域面积，单位㎡")
    private Integer areaSize;

    @Schema(description = "能耗总量")
    private BigDecimal totalEnergy;

    @Schema(description = "单位面积能耗")
    private BigDecimal unitEnergy;

    @Schema(description = "能耗状态：正常能耗/能耗异常", example = "2")
    private String energyStatus;

    @Schema(description = "关联设备数", example = "28977")
    private Integer deviceCount;

    @Schema(description = "同比变化")
    private BigDecimal yoyChange;

    @Schema(description = "环比变化")
    private BigDecimal momChange;

    @Schema(description = "操作人账号")
    private String handleUser;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}
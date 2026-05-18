package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.areamonitor.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 分区能耗分页 Request VO")
@Data
public class AreaMonitorPageReqVO extends PageParam {

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

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}
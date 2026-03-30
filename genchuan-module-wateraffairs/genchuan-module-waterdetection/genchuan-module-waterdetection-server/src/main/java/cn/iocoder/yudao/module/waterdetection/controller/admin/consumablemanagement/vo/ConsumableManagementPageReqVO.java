package cn.iocoder.yudao.module.waterdetection.controller.admin.consumablemanagement.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 耗材库存与更换管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConsumableManagementPageReqVO extends PageParam {

    @Schema(description = "耗材ID")
    private String consumableId;

    @Schema(description = "耗材类型")
    private String consumableType;

    @Schema(description = "库存余量")
    private Double stockQuantity;

    @Schema(description = "预警阈值")
    private Double warningThreshold;

    @Schema(description = "上次更换日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastReplacementDate;

    @Schema(description = "预计下次更换日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] nextReplacementDate;

    @Schema(description = "更换数量")
    private Double replacementQuantity;

    @Schema(description = "关联设备ID")
    private String relatedEquipmentId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
package cn.iocoder.yudao.module.envir.controller.admin.equipment.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EquipmentPageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "11027")
    private String sysEquipmentId;

    @Schema(description = "设备名称", example = "芋艿")
    private String name;

    @Schema(description = "设备编码")
    private String code;

    @Schema(description = "设备类型", example = "1")
    private String type;

    @Schema(description = "设备型号")
    private String model;

    @Schema(description = "规格参数")
    private String specification;

    @Schema(description = "维护周期（单位：天）")
    private Integer maintenanceCycle;

    @Schema(description = "状态：启用/禁用", example = "1")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
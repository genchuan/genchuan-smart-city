package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.all.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 户外广告分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OutdoorAdPageReqVO extends PageParam {

    @Schema(description = "广告ID", example = "24013")
    private String outdoorAdId;

    @Schema(description = "广告名称", example = "芋艿")
    private String name;

    @Schema(description = "广告位置")
    private String location;

    @Schema(description = "审批尺寸")
    private String approvedSize;

    @Schema(description = "实际尺寸")
    private String actualSize;

    @Schema(description = "倾斜角度")
    private BigDecimal tiltAngle;

    @Schema(description = "破损状态", example = "10496")
    private String damageStatusId;

    @Schema(description = "广告状态", example = "27560")
    private String adStatusId;

    @Schema(description = "所属区域")
    private String areaCode;

    @Schema(description = "监管员", example = "4257")
    private String supervisorId;

    @Schema(description = "预警类型", example = "23563")
    private String warningTypeId;

    @Schema(description = "预警时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] warningTime;

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
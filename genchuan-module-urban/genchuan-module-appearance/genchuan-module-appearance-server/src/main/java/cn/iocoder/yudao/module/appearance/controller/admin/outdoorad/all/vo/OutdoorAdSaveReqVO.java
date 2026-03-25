package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.all.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 户外广告新增/修改 Request VO")
@Data
public class OutdoorAdSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19511")
    private Long id;

    @Schema(description = "广告ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24013")
    @NotEmpty(message = "广告ID不能为空")
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
    private LocalDateTime warningTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
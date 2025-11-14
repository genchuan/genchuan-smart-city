package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 基础监测点位新增/修改 Request VO")
@Data
public class BasicMonPtSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "点位ID")
    private String ptId;

    @Schema(description = "点位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "点位名称不能为空")
    private String ptName;

    @Schema(description = "点位编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "点位编码不能为空")
    private String ptCode;

    @Schema(description = "点位类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "点位类型不能为空")
    private String ptType;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属区域代码不能为空")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属区域名称不能为空")
    private String areaName;

    @Schema(description = "关联设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联设备编码不能为空")
    private String deviceCode;

    @Schema(description = "关联设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联设备名称不能为空")
    private String deviceName;

    @Schema(description = "点位坐标X(度)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "点位坐标X(度)不能为空")
    private BigDecimal ptCoordX;

    @Schema(description = "点位坐标Y(度)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "点位坐标Y(度)不能为空")
    private BigDecimal ptCoordY;

    @Schema(description = "负责人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "负责人ID不能为空")
    private Long mngrId;

    @Schema(description = "负责人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "负责人姓名不能为空")
    private String mngrName;

    @Schema(description = "负责人联系方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "负责人联系方式不能为空")
    private String mngrPhone;

    @Schema(description = "点位状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "点位状态不能为空")
    private Boolean ptStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "更新时间不能为空")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
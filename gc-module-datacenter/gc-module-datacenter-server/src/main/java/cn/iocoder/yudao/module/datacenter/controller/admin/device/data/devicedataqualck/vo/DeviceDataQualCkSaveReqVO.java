package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备数据质量检查新增/修改 Request VO")
@Data
public class DeviceDataQualCkSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "质量检查ID")
    private String qualCkId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备ID不能为空")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编码不能为空")
    private String deviceCode;

    @Schema(description = "检查批次", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检查批次不能为空")
    private String ckBatch;

    @Schema(description = "检查时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "检查时间不能为空")
    private LocalDateTime ckTime;

    @Schema(description = "检查项", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检查项不能为空")
    private String ckItem;

    @Schema(description = "检查结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检查结果不能为空")
    private String ckResult;

    @Schema(description = "错误描述")
    private String errorDesc;

    @Schema(description = "整改建议")
    private String rectifySugg;

    @Schema(description = "检查人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检查人不能为空")
    private String ckUser;

    @Schema(description = "整改状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改状态不能为空")
    private String rectifyStatus;

    @Schema(description = "整改时间")
    private LocalDateTime rectifyTime;

    @Schema(description = "整改人")
    private String rectifyUser;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
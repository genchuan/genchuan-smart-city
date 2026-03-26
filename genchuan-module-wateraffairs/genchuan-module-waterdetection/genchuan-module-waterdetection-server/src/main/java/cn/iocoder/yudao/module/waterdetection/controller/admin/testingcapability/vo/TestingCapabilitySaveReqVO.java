package cn.iocoder.yudao.module.waterdetection.controller.admin.testingcapability.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 检测能力及设备管理新增/修改 Request VO")
@Data
public class TestingCapabilitySaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "机构编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "机构编号不能为空")
    private String agencyCode;

    @Schema(description = "可检测指标")
    private String testableIndicators;

    @Schema(description = "设备型号")
    private String equipmentModel;

    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编号不能为空")
    private String equipmentNo;

    @Schema(description = "校准记录")
    private String calibrationRecord;

    @Schema(description = "设备状态(正常/维修中/停用)")
    private String equipmentStatus;

}
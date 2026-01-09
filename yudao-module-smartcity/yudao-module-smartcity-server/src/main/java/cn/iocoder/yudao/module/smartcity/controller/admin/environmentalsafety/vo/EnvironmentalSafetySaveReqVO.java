package cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 环卫设施安全新增/修改 Request VO")
@Data
public class EnvironmentalSafetySaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24563")
    private Long id;

    @Schema(description = "设施名称", example = "芋艿")
    private String facilityName;

    @Schema(description = "设施类型", example = "2")
    private String facilityType;

    @Schema(description = "设施位置")
    private String facilityLocation;

    @Schema(description = "设施编号")
    private String facilityNumber;

    @Schema(description = "运行状态")
    private String runningState;

    @Schema(description = "故障代码")
    private String faultCode;

    @Schema(description = "维修记录")
    private String maintenanceRecord;

    @Schema(description = "维修时间")
    private String maintenanceTime;

    @Schema(description = "异味浓度")
    private String odorConcentration;

    @Schema(description = "有害气体含量")
    private String harmfulGasContent;

    @Schema(description = "消毒记录")
    private String disinfectionRecord;

    @Schema(description = "蝇虫密度")
    private String flyAndInsectDensity;

    @Schema(description = "设施使用频率")
    private String facilityUsageFrequency;

    @Schema(description = "投诉记录")
    private String complaintRecord;

}
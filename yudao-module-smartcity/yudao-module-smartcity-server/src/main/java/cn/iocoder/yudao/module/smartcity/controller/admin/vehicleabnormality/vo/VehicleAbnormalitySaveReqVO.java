package cn.iocoder.yudao.module.smartcity.controller.admin.vehicleabnormality.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车辆异常新增/修改 Request VO")
@Data
public class VehicleAbnormalitySaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "29549")
    private Long id;

    @Schema(description = "车辆编号")
    private String vehicleNumber;

    @Schema(description = "异常发生时间")
    private LocalDateTime abnormalOccurrenceTime;

    @Schema(description = "异常地点")
    private String abnormalLocation;

    @Schema(description = "异常描述", example = "你猜")
    private String abnormalDescription;

    @Schema(description = "车辆状态", example = "1")
    private String vehicleStatus;

    @Schema(description = "影响程度")
    private String degreeOfImpact;

    @Schema(description = "处理措施")
    private String handlingMeasures;

    @Schema(description = "处理时间")
    private LocalDateTime processingTime;

    @Schema(description = "处理人员")
    private String processingPersonnel;

    @Schema(description = "维修费用")
    private String maintenanceCosts;

}
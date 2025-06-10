package cn.iocoder.yudao.module.smartcity.controller.admin.roadcollapsesafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 路面塌陷安全新增/修改 Request VO")
@Data
public class RoadCollapseSafetySaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "11959")
    private Long id;

    @Schema(description = "监测点名称", example = "李四")
    private String monitoringPointName;

    @Schema(description = "监测点位置")
    private String locationMonitoringPoints;

    @Schema(description = "路面状况")
    private String roadCondition;

    @Schema(description = "地下空洞信息")
    private String undergroundCavityInformation;

    @Schema(description = "土壤湿度")
    private String soilMoisture;

    @Schema(description = "土壤位移数据")
    private String soilDisplacementData;

    @Schema(description = "周边建筑物沉降情况")
    private String settlementSurroundingBuildings;

    @Schema(description = "地下水位变化")
    private String changesGroundwaterLevel;

    @Schema(description = "降雨情况")
    private String rainfallSituation;

    @Schema(description = "交通流量")
    private String trafficFlow;

    @Schema(description = "重型车辆通行情况")
    private String trafficHeavyVehicles;

    @Schema(description = "预警级别")
    private String warningLevel;

    @Schema(description = "预警时间")
    private LocalDateTime warningTime;

    @Schema(description = "专家评估意见")
    private String expertEvaluationOpinions;

    @Schema(description = "历史塌陷记录")
    private String historicalCollapseRecords;

}
package cn.iocoder.yudao.module.smartcity.controller.admin.roadcollapsesafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 路面塌陷安全 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadCollapseSafetyRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "11959")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "监测点名称", example = "李四")
    @ExcelProperty("监测点名称")
    private String monitoringPointName;

    @Schema(description = "监测点位置")
    @ExcelProperty("监测点位置")
    private String locationMonitoringPoints;

    @Schema(description = "路面状况")
    @ExcelProperty("路面状况")
    private String roadCondition;

    @Schema(description = "地下空洞信息")
    @ExcelProperty("地下空洞信息")
    private String undergroundCavityInformation;

    @Schema(description = "土壤湿度")
    @ExcelProperty("土壤湿度")
    private String soilMoisture;

    @Schema(description = "土壤位移数据")
    @ExcelProperty("土壤位移数据")
    private String soilDisplacementData;

    @Schema(description = "周边建筑物沉降情况")
    @ExcelProperty("周边建筑物沉降情况")
    private String settlementSurroundingBuildings;

    @Schema(description = "地下水位变化")
    @ExcelProperty("地下水位变化")
    private String changesGroundwaterLevel;

    @Schema(description = "降雨情况")
    @ExcelProperty("降雨情况")
    private String rainfallSituation;

    @Schema(description = "交通流量")
    @ExcelProperty("交通流量")
    private String trafficFlow;

    @Schema(description = "重型车辆通行情况")
    @ExcelProperty("重型车辆通行情况")
    private String trafficHeavyVehicles;

    @Schema(description = "预警级别")
    @ExcelProperty("预警级别")
    private String warningLevel;

    @Schema(description = "预警时间")
    @ExcelProperty("预警时间")
    private LocalDateTime warningTime;

    @Schema(description = "专家评估意见")
    @ExcelProperty("专家评估意见")
    private String expertEvaluationOpinions;

    @Schema(description = "历史塌陷记录")
    @ExcelProperty("历史塌陷记录")
    private String historicalCollapseRecords;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
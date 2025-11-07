package cn.iocoder.yudao.module.smartcity.controller.admin.eventstatisticalanalysis.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 事件统计分析新增/修改 Request VO")
@Data
public class EventStatisticalAnalysisSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "12371")
    private Long id;

    @Schema(description = "统计周期")
    private String statisticalCycle;

    @Schema(description = "事件类型", example = "2")
    private String eventType;

    @Schema(description = "事件数量")
    private String numberOfEvents;

    @Schema(description = "发生地点")
    private String place;

    @Schema(description = "高发时段")
    private String highIncidencePeriod;

    @Schema(description = "平均处理时长")
    private String aht;

    @Schema(description = "处理成功率")
    private String processingSuccessRate;

    @Schema(description = "严重程度分布")
    private String severityDistribution;

    @Schema(description = "涉及部门")
    private String involvedDepartments;

    @Schema(description = "责任主体")
    private String responsibleParty;

    @Schema(description = "处理方式占比")
    private String proportionOfProcessing;

    @Schema(description = "资源消耗统计")
    private String resourceConsumption;

    @Schema(description = "不同渠道上报占比")
    private String proportionOfReporting;

    @Schema(description = "重复事件数量")
    private String numberOfRepeatedEvents;

    @Schema(description = "时间序列趋势")
    private String timeSeriesTrend;

    @Schema(description = "关联部件故障次数")
    private String numberOfRelated;

    @Schema(description = "公众反馈满意度评分")
    private String publicFeedbackSatisfaction;

}
package cn.iocoder.yudao.module.smartcity.controller.admin.eventstatisticalanalysis.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 事件统计分析 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EventStatisticalAnalysisRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "12371")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "统计周期")
    @ExcelProperty("统计周期")
    private String statisticalCycle;

    @Schema(description = "事件类型", example = "2")
    @ExcelProperty("事件类型")
    private String eventType;

    @Schema(description = "事件数量")
    @ExcelProperty("事件数量")
    private String numberOfEvents;

    @Schema(description = "发生地点")
    @ExcelProperty("发生地点")
    private String place;

    @Schema(description = "高发时段")
    @ExcelProperty("高发时段")
    private String highIncidencePeriod;

    @Schema(description = "平均处理时长")
    @ExcelProperty("平均处理时长")
    private String aht;

    @Schema(description = "处理成功率")
    @ExcelProperty("处理成功率")
    private String processingSuccessRate;

    @Schema(description = "严重程度分布")
    @ExcelProperty("严重程度分布")
    private String severityDistribution;

    @Schema(description = "涉及部门")
    @ExcelProperty("涉及部门")
    private String involvedDepartments;

    @Schema(description = "责任主体")
    @ExcelProperty("责任主体")
    private String responsibleParty;

    @Schema(description = "处理方式占比")
    @ExcelProperty("处理方式占比")
    private String proportionOfProcessing;

    @Schema(description = "资源消耗统计")
    @ExcelProperty("资源消耗统计")
    private String resourceConsumption;

    @Schema(description = "不同渠道上报占比")
    @ExcelProperty("不同渠道上报占比")
    private String proportionOfReporting;

    @Schema(description = "重复事件数量")
    @ExcelProperty("重复事件数量")
    private String numberOfRepeatedEvents;

    @Schema(description = "时间序列趋势")
    @ExcelProperty("时间序列趋势")
    private String timeSeriesTrend;

    @Schema(description = "关联部件故障次数")
    @ExcelProperty("关联部件故障次数")
    private String numberOfRelated;

    @Schema(description = "公众反馈满意度评分")
    @ExcelProperty("公众反馈满意度评分")
    private String publicFeedbackSatisfaction;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
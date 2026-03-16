package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning;

import cn.iocoder.yudao.module.envirhealth.util.vo.CompletionRatePointVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RoadCleaningExecutingRespVO {

    @Schema(description = "卡片：当前作业任务数（执行中的计划数量）")
    private Long currentTaskCount;

    @Schema(description = "卡片：正常运行数（执行中且无异常的计划数量）")
    private Long normalRunningCount;

    @Schema(description = "卡片：异常标记数（执行中有异常的计划数量）")
    private Long abnormalCount;

    @Schema(description = "基础折线图：当日清扫路段完成率实时增长趋势（每小时的数据点）")
    private List<CompletionRatePointVO> completionRateTrend;
}
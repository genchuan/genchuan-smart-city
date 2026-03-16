package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning;

import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.LineItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "环境卫生管理 - 道路清扫已完成统计 Response VO")
@Data
public class RoadCleaningCompletedRespVO {

    @Schema(description = "卡片数据 - 已完成任务总数", example = "156")
    private Long completedTaskCount;

    @Schema(description = "卡片数据 - 总清扫里程", example = "1250.5")
    private Double totalCleaningMileage;

    @Schema(description = "卡片数据 - 平均质量达标率", example = "94.5")
    private Double avgQualityRate;

    @Schema(description = "卡片数据 - 问题处置及时率", example = "92.3")
    private Double problemHandleRate;

    @Schema(description = "基础柱状图 - 按日/周/月任务完成量对比")
    private List<BarItemVO> taskCompletionComparison;

    @Schema(description = "基础折线图 - 质量达标率趋势变化")
    private List<LineItemVO> qualityRateTrend;

    @Schema(description = "圆环图 - 各区域完成量占比")
    private List<PieItemVO> areaCompletionDistribution;

    @Schema(description = "圆环图 - 各人员作业量占比")
    private List<PieItemVO> staffWorkloadDistribution;
}
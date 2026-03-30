package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "环境卫生管理 - 道路清扫核查统计 Response VO")
@Data
public class RoadCleaningCheckRespVO {

    @Schema(description = "卡片数据 - 待核查任务数", example = "12")
    private Long pendingCheckCount;

    @Schema(description = "卡片数据 - 已达标数", example = "45")
    private Long qualifiedCount;

    @Schema(description = "卡片数据 - 需整改数", example = "8")
    private Long needReformCount;

    @Schema(description = "圆环图 - 核查结果占比")
    private List<PieItemVO> checkResultDistribution;

    @Schema(description = "圆环图 - 区域分布占比")
    private List<PieItemVO> areaDistribution;

    @Schema(description = "基础柱状图 - 不同区域质量达标率对比")
    private List<BarItemVO> areaQualityRateList;
}
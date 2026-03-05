package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.completed;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "环境卫生管理模块 - 收运计划已完成任务卡片统计 Response VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GarbageCollectionCardCompletedVO {

    @Schema(description = "已完成任务总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "128")
    private Integer completedTaskCount;

    @Schema(description = "总收运量(吨)", requiredMode = Schema.RequiredMode.REQUIRED, example = "356.75")
    private BigDecimal totalCollectedVolume;

    @Schema(description = "平均完成率(%)", requiredMode = Schema.RequiredMode.REQUIRED, example = "92.5")
    private BigDecimal averageCompletionRate;

    @Schema(description = "异常办结率(%)", requiredMode = Schema.RequiredMode.REQUIRED, example = "85.3")
    private BigDecimal abnormalCompleteRate;
}
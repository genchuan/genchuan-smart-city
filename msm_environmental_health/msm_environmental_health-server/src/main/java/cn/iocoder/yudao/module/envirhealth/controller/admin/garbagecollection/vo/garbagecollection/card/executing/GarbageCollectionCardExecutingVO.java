package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.executing;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/28 12:54
 */
@Schema(description = "环境卫生管理 - 收运计划卡片统计数据 Response VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GarbageCollectionCardExecutingVO {
    @Schema(description = "当前作业任务数（执行中 + 异常）", requiredMode = Schema.RequiredMode.REQUIRED, example = "15")
    private Integer currentTaskCount;

    @Schema(description = "正常运行数（执行中）", requiredMode = Schema.RequiredMode.REQUIRED, example = "8")
    private Integer normalRunningCount;

    @Schema(description = "异常标记数（异常）", requiredMode = Schema.RequiredMode.REQUIRED, example = "7")
    private Integer abnormalCount;
}
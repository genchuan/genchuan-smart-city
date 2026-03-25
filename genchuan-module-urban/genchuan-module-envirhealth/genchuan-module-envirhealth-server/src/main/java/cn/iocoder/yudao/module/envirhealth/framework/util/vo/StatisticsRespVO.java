package cn.iocoder.yudao.module.envirhealth.framework.util.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;
/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/28 11:46
 */

@Schema(description = "环境卫生管理 - 统计数据 Response VO")
@Data
public class StatisticsRespVO {

    @Schema(description = "总计划数", requiredMode = Schema.RequiredMode.REQUIRED, example = "35")
    private Integer total;

    @Schema(description = "各状态计划数量", requiredMode = Schema.RequiredMode.REQUIRED)
    private Map<String, Integer> planStatusCounts;
}
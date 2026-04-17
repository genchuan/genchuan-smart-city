package cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Schema(description = "管理后台 - 奖助勤贷 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AidWorkChartRespVO {

    @Schema(description = "总申请数")
    private Long totalApplyCount;

    @Schema(description = "总通过数")
    private Long totalPassCount;

    @Schema(description = "总申请金额")
    private Double totalApplyAmount;

    @Schema(description = "总发放金额")
    private Double totalGrantAmount;

    @Schema(description = "各状态申请数量统计")
    private Map<String, Long> statusCountMap;

    @Schema(description = "各资助类型申请数量统计")
    private Map<String, Long> typeCountMap;




}

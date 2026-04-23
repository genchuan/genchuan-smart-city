package cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 奖助勤贷 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AidWorkApplyCountRespVO {

    @Schema(description = "资助类型编码")
    private String type;
    @Schema(description = "资助类型名称")
    private String name;
    @Schema(description = "该类型申请人数")
    private Long applyCount;
    @Schema(description = "该类型办理完成人数")
    private Long finishCount;
    @Schema(description = "该类型办理完成率，百分比保留 2 位小数")
    private BigDecimal finishRate;

}

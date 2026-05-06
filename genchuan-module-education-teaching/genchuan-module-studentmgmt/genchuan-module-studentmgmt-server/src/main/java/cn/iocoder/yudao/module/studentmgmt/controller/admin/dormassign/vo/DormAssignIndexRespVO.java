package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 分配核心指标统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DormAssignIndexRespVO {

    @Schema(description = "需分配宿舍的新生总人数")
    private Integer totalStudentCount;
    @Schema(description = "已完成分配人数")
    private Integer assignedCount;
    @Schema(description = "宿舍分配完成率")
    private BigDecimal assignRate;
    @Schema(description = "剩余空床位数")
    private Integer emptyBedCount;


}

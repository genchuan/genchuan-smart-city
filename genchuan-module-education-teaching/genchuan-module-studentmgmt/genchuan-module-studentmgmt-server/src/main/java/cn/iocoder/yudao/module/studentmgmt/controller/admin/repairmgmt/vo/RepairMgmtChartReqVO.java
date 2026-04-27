package cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 反馈 Request VO")
@Data
public class RepairMgmtChartReqVO {

    @Schema(description = "统计时间范围", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime[] timeRange ;

    @Schema(description = "栋号")
    private String dormBuilding ;


}
package cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周末留宿统计看板 Request VO")
@Data
public class StayMgmtChartReqVO {

    @Schema(description = "统计时间范围", requiredMode = Schema.RequiredMode.REQUIRED, example = "10528")
    private LocalDateTime[] timeRange ;

    @Schema(description = "班级 ID")
    private Long classId;

}
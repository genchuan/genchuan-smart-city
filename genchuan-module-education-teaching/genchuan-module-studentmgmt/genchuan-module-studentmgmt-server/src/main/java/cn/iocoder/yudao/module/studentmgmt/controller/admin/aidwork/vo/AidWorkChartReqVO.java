package cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 奖助勤贷统计看板 Request VO")
@Data
public class AidWorkChartReqVO {

    @Schema(description = "统计开始时间")
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间")
    private LocalDateTime endTime;


}
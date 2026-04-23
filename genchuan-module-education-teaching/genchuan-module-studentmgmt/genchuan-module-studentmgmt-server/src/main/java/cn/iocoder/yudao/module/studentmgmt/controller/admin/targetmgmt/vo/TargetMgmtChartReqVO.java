package cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 德育指标配置看板 Request VO")
@Data
public class TargetMgmtChartReqVO {

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "1756684800000")
    private LocalDateTime startTime;
    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "1756694800000")
    private LocalDateTime endTime;

}
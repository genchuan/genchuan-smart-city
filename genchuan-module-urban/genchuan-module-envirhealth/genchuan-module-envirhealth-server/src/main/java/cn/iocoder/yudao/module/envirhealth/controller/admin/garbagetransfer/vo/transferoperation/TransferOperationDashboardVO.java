package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "转运作业仪表盘统计 Response VO")
@Data
public class TransferOperationDashboardVO {

    @Schema(description = "当前作业总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Long totalCount;

    @Schema(description = "正常运行数", requiredMode = Schema.RequiredMode.REQUIRED, example = "80")
    private Long normalCount;

    @Schema(description = "异常标记数", requiredMode = Schema.RequiredMode.REQUIRED, example = "20")
    private Long abnormalCount;
}
package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "场站资源报表生成")
public class StationOpReportCreateReqVO {
    @NotBlank(message = "报表周期不能为空")
    @Schema(description = "报表周期", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reportCycle;

//    @NotNull(message = "开始时间不能为空")
    @Schema(description = "开始时间 yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime reportStartTime;

//    @NotNull(message = "结束时间不能为空")
    @Schema(description = "结束时间 yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime reportEndTime;

    @Schema(description = "备注")
    private String remark;
}

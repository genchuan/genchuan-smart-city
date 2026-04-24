package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.extraops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "场站资源历史报表生成 Request VO")
public class StationOpHistoryReportCreateReqVO {

    @NotBlank(message = "报表周期不能为空")
    @Schema(description = "报表周期：日报/周报/月报/季报/半年报/年报", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reportCycle;

    @NotNull(message = "目标日期不能为空")
    @Schema(description = "目标日期（要查哪一天/哪一周/哪一月的基准日期）,时间戳", requiredMode = Schema.RequiredMode.REQUIRED,example = "1775011986000")
    private LocalDateTime targetDate;

    @Schema(description = "备注")
    private String remark;
}

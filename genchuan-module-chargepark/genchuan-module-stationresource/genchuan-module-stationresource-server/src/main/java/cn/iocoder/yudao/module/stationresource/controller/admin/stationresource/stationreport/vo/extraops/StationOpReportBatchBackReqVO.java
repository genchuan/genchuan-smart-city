package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.extraops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "批量回溯生成报表 Request VO")
public class StationOpReportBatchBackReqVO {

    @NotBlank(message = "报表周期不能为空")
    @Schema(description = "日报/周报/月报/季报/半年报/年报", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reportCycle;

    @NotNull(message = "回溯数量不能为空")
    @Schema(description = "往前回溯 N 个周期", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer backNum;

    @Schema(description = "备注")
    private String remark;
}

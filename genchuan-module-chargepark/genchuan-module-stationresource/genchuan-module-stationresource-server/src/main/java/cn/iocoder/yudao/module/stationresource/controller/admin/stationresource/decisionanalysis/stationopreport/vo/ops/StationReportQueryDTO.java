package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.ops;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class StationReportQueryDTO {

    @NotBlank(message = "开始时间不能为空")
    private String startTime;

    @NotBlank(message = "结束时间不能为空")
    private String endTime;

    @NotBlank(message = "周期类型不能为空")
    private String cycleType; // daily/weekly/monthly/quarterly/halfyear/year/custom
}

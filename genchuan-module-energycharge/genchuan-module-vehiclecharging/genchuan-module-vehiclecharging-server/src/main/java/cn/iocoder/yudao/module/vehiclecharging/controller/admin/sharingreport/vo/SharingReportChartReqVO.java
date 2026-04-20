package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SharingReportChartReqVO {

    @NotBlank
    private String reportType;

    @NotBlank
    private String timeRange;
}

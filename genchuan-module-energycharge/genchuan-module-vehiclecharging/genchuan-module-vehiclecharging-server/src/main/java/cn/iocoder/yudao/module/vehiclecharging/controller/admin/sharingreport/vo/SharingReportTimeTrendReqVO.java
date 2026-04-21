package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SharingReportTimeTrendReqVO {
    @NotBlank
    private String reportType;
    @NotBlank
    private String timeRange;
}

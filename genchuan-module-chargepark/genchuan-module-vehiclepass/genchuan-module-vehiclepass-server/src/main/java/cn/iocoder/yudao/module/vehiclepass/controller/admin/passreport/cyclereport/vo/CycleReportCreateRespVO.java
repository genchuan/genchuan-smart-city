package cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 创建周期报表响应 VO")
@Data
public class CycleReportCreateRespVO {

    @Schema(description = "报表记录ID")
    private Long id;

    @Schema(description = "报表生成状态")
    private String reportStatus;

}
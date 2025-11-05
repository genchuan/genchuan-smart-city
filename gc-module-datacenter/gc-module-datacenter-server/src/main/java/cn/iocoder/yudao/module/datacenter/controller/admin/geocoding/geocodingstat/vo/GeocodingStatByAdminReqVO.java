package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Schema(description = "管理后台 - 按行政区域统计 Request VO")
@Data
public class GeocodingStatByAdminReqVO {

    @Schema(description = "行政区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区域代码不能为空")
    private String adminCode;

    @Schema(description = "统计开始时间")
    private String startTime;

    @Schema(description = "统计结束时间")
    private String endTime;
}
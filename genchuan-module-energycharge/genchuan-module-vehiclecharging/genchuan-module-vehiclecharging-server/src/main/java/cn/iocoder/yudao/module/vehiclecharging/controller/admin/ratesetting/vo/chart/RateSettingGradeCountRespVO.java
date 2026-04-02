package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 费率档次数量统计 Response VO")
public class RateSettingGradeCountRespVO {

    @Schema(description = "费率档次类型：基础费率、峰谷分时费率", example = "基础费率")
    private String gradeType;

    @Schema(description = "数量", example = "3")
    private Integer count;
}

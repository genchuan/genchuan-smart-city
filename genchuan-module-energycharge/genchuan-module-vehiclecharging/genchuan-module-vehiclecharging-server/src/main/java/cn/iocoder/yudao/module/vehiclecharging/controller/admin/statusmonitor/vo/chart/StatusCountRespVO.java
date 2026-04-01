package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 设备状态统计 响应VO")
public class StatusCountRespVO {

    @Schema(description = "场站名称", example = "泉州丰泽万达广场充电站")
    private String stationName;

    @Schema(description = "设备数量", example = "8")
    private Integer count;

}

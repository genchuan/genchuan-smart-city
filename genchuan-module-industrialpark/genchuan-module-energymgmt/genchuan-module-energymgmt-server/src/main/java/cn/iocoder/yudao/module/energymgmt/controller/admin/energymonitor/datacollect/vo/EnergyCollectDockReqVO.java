package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 能耗采集对接 Request VO")
@Data
public class EnergyCollectDockReqVO {

    @Schema(description = "设备名称", example = "王霸牌电表")
    private String deviceName;

    @Schema(description = "设备类型：电表/水表/气表", example = "电表")
    private String deviceType;

    @Schema(description = "能耗类型：电/水/气/热", example = "电")
    private String energyType;

    @Schema(description = "采集频率，单位分钟")
    private Integer collectFreq;

}

package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.newvo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StatusMonitorHandleAbnormalReqVO {
    @NotNull
    private List<Long> ids;
    @NotBlank
    private String disposeMeasure;      // 处置措施





}

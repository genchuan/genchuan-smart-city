package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RateSettingDisableReqVO {
    @Schema(description = "[批量修改的id列表] ")
    private List<Long> idList;
}

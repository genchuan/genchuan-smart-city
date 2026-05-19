package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo;

import cn.iocoder.yudao.module.energymgmt.framework.utils.BaseIdVO;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 能耗采集校准 Request VO")
@Data
public class EnergyCollectCalibrateReqVO extends BaseIdVO {

    @Schema(description = "校准后的能耗数值")
    @TableField(exist = false)
    private BigDecimal calibrateValue;

}

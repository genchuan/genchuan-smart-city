package cn.iocoder.yudao.module.usermerchant.framework.commom.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BaseChartReqVO {

    @Schema(description = "时间范围")
    private String timeRange;

}

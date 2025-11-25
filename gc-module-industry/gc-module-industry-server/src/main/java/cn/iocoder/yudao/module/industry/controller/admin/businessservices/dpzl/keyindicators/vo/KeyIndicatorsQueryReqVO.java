package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.keyindicators.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class KeyIndicatorsQueryReqVO {

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "区域编码")
    private String regionCode;
}
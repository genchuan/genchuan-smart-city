package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 卡种配置精简信息 Response VO")
@Data
public class CardConfigSimpleRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "卡种名称")
    private String name;

}

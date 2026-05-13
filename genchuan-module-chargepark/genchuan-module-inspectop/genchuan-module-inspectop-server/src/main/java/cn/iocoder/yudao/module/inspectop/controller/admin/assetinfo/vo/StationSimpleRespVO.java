package cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "巡查巡检 - 场站简略信息 Response VO")
@Data
public class StationSimpleRespVO {

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "场站名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "泉州万达旗舰充电站")
    private String name;
}
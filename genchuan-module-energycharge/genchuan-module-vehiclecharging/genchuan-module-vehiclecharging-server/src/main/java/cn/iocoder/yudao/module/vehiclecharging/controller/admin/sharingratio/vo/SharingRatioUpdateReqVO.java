package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分账比例编辑 Request VO")
@Data
public class SharingRatioUpdateReqVO extends SharingRatioCreateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long id;

}

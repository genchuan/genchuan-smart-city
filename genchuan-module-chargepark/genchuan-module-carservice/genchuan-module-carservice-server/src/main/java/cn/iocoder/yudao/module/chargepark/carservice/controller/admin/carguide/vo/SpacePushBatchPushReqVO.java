package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 空位批量推送 Request VO")
@Data
public class SpacePushBatchPushReqVO {

    @Schema(description = "空位推送 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "空位推送 ID 列表不能为空")
    private List<Long> ids;

}

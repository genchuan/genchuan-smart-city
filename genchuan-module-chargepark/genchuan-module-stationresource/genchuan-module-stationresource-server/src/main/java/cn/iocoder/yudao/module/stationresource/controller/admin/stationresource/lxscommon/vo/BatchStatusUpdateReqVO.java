package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.lxscommon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = " Request VO")
public class BatchStatusUpdateReqVO {

    @Schema(description = "id列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[3,4]")
    @NotEmpty(message = "id列表不能为空")
    private List<Long> ids;

}

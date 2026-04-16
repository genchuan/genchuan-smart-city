package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "片区信息 - 批量禁用 Request VO")
public class AreaInfoDisableReqVO {

    @Schema(description = "片区编号列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[3,4]")
    @NotEmpty(message = "片区编号列表不能为空")
    private List<Long> ids;

}

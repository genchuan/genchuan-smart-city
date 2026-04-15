package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "状态更新请求 VO")
public class StatusUpdateReq {

    @NotEmpty(message = "场站ID列表不能为空")
    @Schema(description = "场站ID集合", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Long> ids;

}

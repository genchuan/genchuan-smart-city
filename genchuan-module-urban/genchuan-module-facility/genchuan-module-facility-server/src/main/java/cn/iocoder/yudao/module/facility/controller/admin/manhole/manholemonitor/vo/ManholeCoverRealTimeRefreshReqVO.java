package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "管理后台 - 井盖实时数据刷新请求VO")
public class ManholeCoverRealTimeRefreshReqVO {

    @Schema(description = "井盖ID列表（为空则刷新当前租户所有井盖）", example = "[100001,100002]")
    private List<Long> coverIds;

    @Schema(description = "租户ID（多租户隔离）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000001")
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

}

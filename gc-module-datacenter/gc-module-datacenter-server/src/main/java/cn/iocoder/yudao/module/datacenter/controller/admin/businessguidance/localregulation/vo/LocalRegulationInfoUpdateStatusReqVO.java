package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.localregulation.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 管理后台 - 地方法规信息更新状态 Request VO
 *
 * @author Gyh
 */
@Data
@Schema(description = "管理后台 - 地方法规信息更新状态 Request VO")
public class LocalRegulationInfoUpdateStatusReqVO {

    @Schema(description = "ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "法规状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "已废止")
    @NotNull(message = "法规状态不能为空")
    private String regulationStatus;
}
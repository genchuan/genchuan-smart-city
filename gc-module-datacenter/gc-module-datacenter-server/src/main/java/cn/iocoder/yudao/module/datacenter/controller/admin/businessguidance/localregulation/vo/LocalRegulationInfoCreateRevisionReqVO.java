package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.localregulation.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;

/**
 * 管理后台 - 地方法规信息创建修订版本 Request VO
 *
 * @author Gyh
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "管理后台 - 地方法规信息创建修订版本 Request VO")
public class LocalRegulationInfoCreateRevisionReqVO extends LocalRegulationInfoBaseVO {

    @Schema(description = "原法规ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "原法规ID不能为空")
    private Long originalId;
}
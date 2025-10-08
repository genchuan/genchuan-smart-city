package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.industrystandard.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;

/**
 * 管理后台 - 行业规范信息更新 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "管理后台 - 行业规范信息更新 Request VO")
public class IndustryStandardInfoUpdateReqVO extends IndustryStandardInfoBaseVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "主键ID不能为空")
    private Long id;
}
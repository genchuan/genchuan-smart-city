package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.industrystandard.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;

/**
 * 管理后台 - 行业规范信息创建替代版本 Request VO
 *
 * @author Gyh
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "管理后台 - 行业规范信息创建替代版本 Request VO")
public class IndustryStandardInfoCreateReplaceReqVO extends IndustryStandardInfoBaseVO {

    @Schema(description = "原规范ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "原规范ID不能为空")
    private Long originalId;
}
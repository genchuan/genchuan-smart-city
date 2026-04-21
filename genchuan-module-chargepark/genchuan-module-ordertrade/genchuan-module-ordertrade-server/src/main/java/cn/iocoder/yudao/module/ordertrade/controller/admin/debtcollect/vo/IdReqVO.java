package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 业务操作通用请求 VO（pay/refund/cancel/approve 等只需要 id 的操作）
 *
 * @author genchuan
 */
@Schema(description = "业务操作通用 Request VO")
@Data
public class IdReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "ID不能为空")
    private Long id;

    @Schema(description = "备注/原因（取消、驳回等操作时填写）")
    private String remark;
}

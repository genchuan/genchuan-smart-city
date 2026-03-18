package cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.template;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DraftPunishNoticeReq {
    @Schema(description = "[处罚通知书ID]")
    @NotNull(message = "[处罚通知书ID] 不能用")
    private Long punishReviewNoticeId;
}

package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 开始复审 Request VO")
public class RectifyReviewStartReqVO {

    @Schema(description = "台账ID", required = true)
    private Long ledgerId;
}

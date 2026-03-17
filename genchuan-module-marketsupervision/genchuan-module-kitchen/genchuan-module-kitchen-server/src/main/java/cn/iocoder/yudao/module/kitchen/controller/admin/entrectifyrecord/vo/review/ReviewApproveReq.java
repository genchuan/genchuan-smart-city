package cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReviewApproveReq {
    @Schema(description = "[企业整改记录id]", example = "1")
    private Long entRectifyRecordId;
}

package cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReviewRejectReq {
    @Schema(description = "[企业整改记录id]", example = "1")
    private Long entRectifyRecordId;


    @Schema(description = "[驳回原因]", example = "资料不完整")
    private String rejectReason;
}

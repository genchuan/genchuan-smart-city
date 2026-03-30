package cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 整改通知书 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RectifyNoticeRespVO {

    @Schema(description = "[主键ID] 整改通知书唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "20231")
    @ExcelProperty("[主键ID] 整改通知书唯一标识")
    private Long id;

    @Schema(description = "[整改通知书编号] 唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[整改通知书编号] 唯一编号")
    private String noticeCode;

    @Schema(description = "[整改复审台账ID] 关联park_rectify_review.id，唯一", requiredMode = Schema.RequiredMode.REQUIRED, example = "13277")
    @ExcelProperty("[整改复审台账ID] 关联park_rectify_review.id，唯一")
    private Long rectifyReviewId;

    @Schema(description = "[下发时间] 通知书正式下发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[下发时间] 通知书正式下发时间")
    private LocalDateTime issueTime;

    @Schema(description = "[整改期限] 要求完成整改的截止日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[整改期限] 要求完成整改的截止日期")
    private LocalDate rectifyDeadline;

    @Schema(description = "[送达状态] 如：未送达/已送达/拒收", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[送达状态] 如：未送达/已送达/拒收")
    private String receiveStatus;

    @Schema(description = "[送达时间] 实际送达或拒收时间")
    @ExcelProperty("[送达时间] 实际送达或拒收时间")
    private LocalDateTime receiveTime;

    @Schema(description = "[通知书原件内容] 富文本内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[通知书原件内容] 富文本内容")
    private String noticeContent;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 预留")
    @ExcelProperty("[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    @ExcelProperty("[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    @ExcelProperty("[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    @ExcelProperty("[通用扩展字段4] 预留")
    private String extCommon4;

}

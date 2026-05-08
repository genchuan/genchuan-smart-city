package cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 家长提交回复 Request VO")
@Data
public class ParentReplySubmitReqVO {

    @Schema(description = "关联消息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1782")
    private Long communicateId;
    @Schema(description = "学生ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "172")
    private Long studentId;
    @Schema(description = "家长回复内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "好的，收到")
    private String parentReplyContent;
    @Schema(description = "家长回复时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2022-07-08 07:30:09")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime parentReplyTime;


}
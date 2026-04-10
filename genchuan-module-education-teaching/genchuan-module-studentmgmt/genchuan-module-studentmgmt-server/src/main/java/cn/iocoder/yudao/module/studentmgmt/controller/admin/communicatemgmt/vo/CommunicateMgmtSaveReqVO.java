package cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 沟通管理新增/修改 Request VO")
@Data
public class CommunicateMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28666")
    private Long id;

    @Schema(description = "消息标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "消息标题不能为空")
    private String title;

    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "消息内容不能为空")
    private String content;

    @Schema(description = "发布人")
    private String sendUser;

    @Schema(description = "发布时间")
    private LocalDateTime sendTime;

    @Schema(description = "家长反馈内容")
    private String replyContent;

    @Schema(description = "反馈时间")
    private LocalDateTime replyTime;

    @Schema(description = "互动率")
    private BigDecimal interactRate;

    @Schema(description = "状态：未发布/已发布", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：未发布/已发布不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}
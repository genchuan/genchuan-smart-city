package cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 家长回复新增/修改 Request VO")
@Data
public class ParentReplySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1782")
    private Long id;

    @Schema(description = "关联沟通消息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12168")
    @NotNull(message = "关联沟通消息ID不能为空")
    private Long communicateId;

    @Schema(description = "学生ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "128")
    @NotNull(message = "学生ID不能为空")
    private Long studentId;

    @Schema(description = "学生姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "学生姓名不能为空")
    private String studentName;

    @Schema(description = "家长回复内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "家长回复内容不能为空")
    private String parentReplyContent;

    @Schema(description = "家长回复时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "家长回复时间不能为空")
    private LocalDateTime parentReplyTime;

    @Schema(description = "老师回复内容")
    private String teacherReplyContent;

    @Schema(description = "老师回复时间")
    private LocalDateTime teacherReplyTime;

    @Schema(description = "阅读状态：未读/已读", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "阅读状态：未读/已读不能为空")
    private String readStatus;

    @Schema(description = "回复状态：未回复/已回复", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "回复状态：未回复/已回复不能为空")
    private String replyStatus;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}
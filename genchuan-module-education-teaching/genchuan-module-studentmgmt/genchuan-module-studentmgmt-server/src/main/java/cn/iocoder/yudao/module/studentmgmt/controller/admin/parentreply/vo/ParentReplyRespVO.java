package cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 家长回复 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParentReplyRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1782")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联沟通消息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12168")
    @ExcelProperty("关联沟通消息ID")
    private Long communicateId;

    @Schema(description = "学生ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "128")
    @ExcelProperty("学生ID")
    private Long studentId;

    @Schema(description = "学生姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("学生姓名")
    private String studentName;

    @Schema(description = "家长回复内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("家长回复内容")
    private String parentReplyContent;

    @Schema(description = "家长回复时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("家长回复时间")
    private LocalDateTime parentReplyTime;

    @Schema(description = "老师回复内容")
    @ExcelProperty("老师回复内容")
    private String teacherReplyContent;

    @Schema(description = "老师回复时间")
    @ExcelProperty("老师回复时间")
    private LocalDateTime teacherReplyTime;

    @Schema(description = "阅读状态：未读/已读", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("阅读状态：未读/已读")
    private String readStatus;

    @Schema(description = "回复状态：未回复/已回复", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("回复状态：未回复/已回复")
    private String replyStatus;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
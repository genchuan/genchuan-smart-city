package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember.vo;


import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评价主体成员 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SubjectMemberRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21677")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "评价主体成员ID（UUID）", example = "18772")
    @ExcelProperty("评价主体成员ID（UUID）")
    private String memberId;

    @Schema(description = "评价主体ID（关联eval_subject.subject_id）", example = "24566")
    @ExcelProperty("评价主体ID（关联eval_subject.subject_id）")
    private String subjectId;

    @Schema(description = "成员用户ID（关联sys_user.user_id）", example = "23703")
    @ExcelProperty("成员用户ID（关联sys_user.user_id）")
    private String userId;

    @Schema(description = "加入时间")
    @ExcelProperty("加入时间")
    private LocalDateTime joinTime;

    @Schema(description = "退出时间，未退出为空")
    @ExcelProperty("退出时间，未退出为空")
    private LocalDateTime exitTime;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "9082")
    @ExcelProperty("状态ID（关联sys_status.status_id）")
    private Integer statusId;

    @Schema(description = "更新时间（业务字段）")
    @ExcelProperty("更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}

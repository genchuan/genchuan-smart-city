package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评价主体成员新增/修改 Request VO")
@Data
public class SubjectMemberSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21677")
    private Long id;

    @Schema(description = "评价主体成员ID（UUID）", example = "18772")
    private String memberId;

    @Schema(description = "评价主体ID（关联eval_subject.subject_id）", example = "24566")
    private String subjectId;

    @Schema(description = "成员用户ID（关联sys_user.user_id）", example = "23703")
    private String userId;

    @Schema(description = "加入时间")
    private LocalDateTime joinTime;

    @Schema(description = "退出时间，未退出为空")
    private LocalDateTime exitTime;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "9082")
    private Integer statusId;

    @Schema(description = "更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
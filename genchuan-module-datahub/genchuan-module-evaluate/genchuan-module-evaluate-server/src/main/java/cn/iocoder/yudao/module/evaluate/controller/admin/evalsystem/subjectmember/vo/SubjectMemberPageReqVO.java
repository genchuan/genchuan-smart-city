package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价主体成员分页 Request VO")
@Data
public class SubjectMemberPageReqVO extends PageParam {

    @Schema(description = "评价主体成员ID（UUID）", example = "18772")
    private String memberId;

    @Schema(description = "评价主体ID（关联eval_subject.subject_id）", example = "24566")
    private String subjectId;

    @Schema(description = "成员用户ID（关联sys_user.user_id）", example = "23703")
    private String userId;

    @Schema(description = "加入时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] joinTime;

    @Schema(description = "退出时间，未退出为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] exitTime;


    @Schema(description = "状态ID（关联sys_status.status_id）", example = "9082")
    private Integer statusId;

    @Schema(description = "更新时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
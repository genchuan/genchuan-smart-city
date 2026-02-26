package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价主体分页 Request VO")
@Data
public class SubjectPageReqVO extends PageParam {

    @Schema(description = "评价主体ID（UUID）", example = "2007")
    private String subjectId;

    @Schema(description = "主体名称", example = "赵六")
    private String name;

    @Schema(description = "主体编码")
    private String code;

    @Schema(description = "主体类型ID（关联sys_subject_type.type_id）", example = "24741")
    private String subjectTypeId;

    @Schema(description = "联系人ID（关联sys_user.user_id）", example = "30257")
    private String contactId;

    @Schema(description = "成员数量", example = "26756")
    private Integer memberCount;

    @Schema(description = "使用次数", example = "26")
    private Integer useCount;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "14834")
    private Integer statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizUpdateTime;

    @Schema(description = "变更日志")
    private String changeLog;

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
package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.questionnaire.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 问卷新增/修改 Request VO")
@Data
public class QuestionnaireSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21207")
    private Long id;

    @Schema(description = "问卷ID（UUID）", example = "8845")
    private String questionnaireId;

    @Schema(description = "问卷名称", example = "李四")
    private String name;

    @Schema(description = "问卷编码")
    private String code;

    @Schema(description = "关联评价任务ID（关联eval_task.task_id）", example = "27154")
    private String taskId;

    @Schema(description = "调查对象范围")
    private String objectScope;

    @Schema(description = "调查对象范围ID（关联sys_scope.scope_id）", example = "1407")
    private String scopeId;

    @Schema(description = "发放方式ID（关联sys_issue_type.type_id）", example = "15036")
    private String issueTypeId;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "原开始时间")
    private LocalDateTime originalStartTime;

    @Schema(description = "原结束时间")
    private LocalDateTime originalEndTime;

    @Schema(description = "填写人数", example = "1504")
    private Integer fillCount;

    @Schema(description = "填写率")
    private BigDecimal fillRate;

    @Schema(description = "平均得分（如95.50）")
    private BigDecimal averageScore;

    @Schema(description = "最终填写率")
    private BigDecimal finalFillRate;

    @Schema(description = "最终平均分")
    private BigDecimal finalAverageScore;

    @Schema(description = "指标值映射结果")
    private String indexValue;

    @Schema(description = "数据关联状态：已关联评价/未关联评价", example = "2")
    private String dataRelationStatus;

    @Schema(description = "问卷链接")
    private String link;

    @Schema(description = "问卷二维码")
    private String qrcode;

    @Schema(description = "问卷状态ID（关联sys_survey_status.status_id）", example = "28166")
    private Integer statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

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
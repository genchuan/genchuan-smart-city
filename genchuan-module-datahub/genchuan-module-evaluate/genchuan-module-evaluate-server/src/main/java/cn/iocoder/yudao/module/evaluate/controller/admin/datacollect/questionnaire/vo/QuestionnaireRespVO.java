package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.questionnaire.vo;

import com.alibaba.excel.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Schema(description = "管理后台 - 问卷 Response VO")
@Data
@ExcelIgnoreUnannotated
public class QuestionnaireRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21207")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "问卷ID（UUID）", example = "8845")
    @ExcelProperty("问卷ID（UUID）")
    private String questionnaireId;

    @Schema(description = "问卷名称", example = "李四")
    @ExcelProperty("问卷名称")
    private String name;

    @Schema(description = "问卷编码")
    @ExcelProperty("问卷编码")
    private String code;

    @Schema(description = "关联评价任务ID（关联eval_task.task_id）", example = "27154")
    @ExcelProperty("关联评价任务ID（关联eval_task.task_id）")
    private String taskId;

    @Schema(description = "调查对象范围")
    @ExcelProperty("调查对象范围")
    private String objectScope;

    @Schema(description = "调查对象范围ID（关联sys_scope.scope_id）", example = "1407")
    @ExcelProperty("调查对象范围ID（关联sys_scope.scope_id）")
    private String scopeId;

    @Schema(description = "发放方式ID（关联sys_issue_type.type_id）", example = "15036")
    @ExcelProperty("发放方式ID（关联sys_issue_type.type_id）")
    private String issueTypeId;

    @Schema(description = "开始时间")
    @ExcelProperty("开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    @ExcelProperty("结束时间")
    private LocalDateTime endTime;

    @Schema(description = "原开始时间")
    @ExcelProperty("原开始时间")
    private LocalDateTime originalStartTime;

    @Schema(description = "原结束时间")
    @ExcelProperty("原结束时间")
    private LocalDateTime originalEndTime;

    @Schema(description = "填写人数", example = "1504")
    @ExcelProperty("填写人数")
    private Integer fillCount;

    @Schema(description = "填写率")
    @ExcelProperty("填写率")
    private BigDecimal fillRate;

    @Schema(description = "平均得分（如95.50）")
    @ExcelProperty("平均得分（如95.50）")
    private BigDecimal averageScore;

    @Schema(description = "最终填写率")
    @ExcelProperty("最终填写率")
    private BigDecimal finalFillRate;

    @Schema(description = "最终平均分")
    @ExcelProperty("最终平均分")
    private BigDecimal finalAverageScore;

    @Schema(description = "指标值映射结果")
    @ExcelProperty("指标值映射结果")
    private String indexValue;

    @Schema(description = "数据关联状态：已关联评价/未关联评价", example = "2")
    @ExcelProperty("数据关联状态：已关联评价/未关联评价")
    private String dataRelationStatus;

    @Schema(description = "问卷链接")
    @ExcelProperty("问卷链接")
    private String link;

    @Schema(description = "问卷二维码")
    @ExcelProperty("问卷二维码")
    private String qrcode;

    @Schema(description = "问卷状态ID（关联sys_survey_status.status_id）", example = "28166")
    @ExcelProperty("问卷状态ID（关联sys_survey_status.status_id）")
    private Integer statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    @ExcelProperty("创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建时间（业务字段）")
    @ExcelProperty("创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

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
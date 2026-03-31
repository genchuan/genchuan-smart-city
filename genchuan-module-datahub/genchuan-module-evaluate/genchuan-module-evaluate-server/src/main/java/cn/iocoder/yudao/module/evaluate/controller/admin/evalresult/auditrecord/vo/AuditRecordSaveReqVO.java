package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.auditrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评价结果审核新增/修改 Request VO")
@Data
public class AuditRecordSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19508")
    private Long id;

    @Schema(description = "审核UUID（主键，UUID）", example = "21849")
    private String auditId;

    @Schema(description = "审核编号")
    private String code;

    @Schema(description = "关联评价任务ID（关联eval_task.task_id）", example = "11008")
    private String taskId;

    @Schema(description = "评价对象ID（关联eval_object.object_id）", example = "32157")
    private String objectId;

    @Schema(description = "评价得分")
    private BigDecimal evalScore;

    @Schema(description = "评价标准ID（关联eval_standard_item.standard_item_id）", example = "1140")
    private String standardId;

    @Schema(description = "审核状态（关联sys_audit_status.status_id）", example = "1")
    private String status;

    @Schema(description = "业务创建时间（创建时间）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "审核人（关联sys_user.user_id）")
    private String auditBy;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "数据来源汇总")
    private String dataSource;

    @Schema(description = "否决项检查结果（通过/触发）")
    private String rejectCheckResult;

    @Schema(description = "驳回原因完整内容", example = "不对")
    private String rejectReason;

    @Schema(description = "审核意见摘要")
    private String auditOpinion;

    @Schema(description = "数据来源明细")
    private String dataSourceDetail;

    @Schema(description = "各指标得分")
    private String indexScore;

    @Schema(description = "分配审核人（关联sys_user.user_id）")
    private String assignBy;

    @Schema(description = "任务创建人（关联sys_user.user_id）")
    private String taskCreateBy;

    @Schema(description = "待审核时长（小时）")
    private BigDecimal waitHour;

    @Schema(description = "问题数据来源")
    private String errorDataSource;

    @Schema(description = "重算次数", example = "8145")
    private Integer recalcCount;

    @Schema(description = "最后重算时间")
    private LocalDateTime recalcTime;

    @Schema(description = "修正指引")
    private String correctGuide;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
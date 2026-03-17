package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.auditrecord.vo;

import com.alibaba.excel.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Schema(description = "管理后台 - 评价结果审核 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AuditRecordRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19508")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "审核UUID（主键，UUID）", example = "21849")
    @ExcelProperty("审核UUID（主键，UUID）")
    private String auditId;

    @Schema(description = "审核编号")
    @ExcelProperty("审核编号")
    private String code;

    @Schema(description = "关联评价任务ID（关联eval_task.task_id）", example = "11008")
    @ExcelProperty("关联评价任务ID（关联eval_task.task_id）")
    private String taskId;

    @Schema(description = "评价对象ID（关联eval_object.object_id）", example = "32157")
    @ExcelProperty("评价对象ID（关联eval_object.object_id）")
    private String objectId;

    @Schema(description = "评价得分")
    @ExcelProperty("评价得分")
    private BigDecimal evalScore;

    @Schema(description = "评价标准ID（关联eval_standard_item.standard_item_id）", example = "1140")
    @ExcelProperty("评价标准ID（关联eval_standard_item.standard_item_id）")
    private String standardId;

    @Schema(description = "审核状态（关联sys_audit_status.status_id）", example = "1")
    @ExcelProperty("审核状态（关联sys_audit_status.status_id）")
    private String status;

    @Schema(description = "业务创建时间（创建时间）")
    @ExcelProperty("业务创建时间（创建时间）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "审核人（关联sys_user.user_id）")
    @ExcelProperty("审核人（关联sys_user.user_id）")
    private String auditBy;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "数据来源汇总")
    @ExcelProperty("数据来源汇总")
    private String dataSource;

    @Schema(description = "否决项检查结果（通过/触发）")
    @ExcelProperty("否决项检查结果（通过/触发）")
    private String rejectCheckResult;

    @Schema(description = "驳回原因完整内容", example = "不对")
    @ExcelProperty("驳回原因完整内容")
    private String rejectReason;

    @Schema(description = "审核意见摘要")
    @ExcelProperty("审核意见摘要")
    private String auditOpinion;

    @Schema(description = "数据来源明细")
    @ExcelProperty("数据来源明细")
    private String dataSourceDetail;

    @Schema(description = "各指标得分")
    @ExcelProperty("各指标得分")
    private String indexScore;

    @Schema(description = "分配审核人（关联sys_user.user_id）")
    @ExcelProperty("分配审核人（关联sys_user.user_id）")
    private String assignBy;

    @Schema(description = "任务创建人（关联sys_user.user_id）")
    @ExcelProperty("任务创建人（关联sys_user.user_id）")
    private String taskCreateBy;

    @Schema(description = "待审核时长（小时）")
    @ExcelProperty("待审核时长（小时）")
    private BigDecimal waitHour;

    @Schema(description = "问题数据来源")
    @ExcelProperty("问题数据来源")
    private String errorDataSource;

    @Schema(description = "重算次数", example = "8145")
    @ExcelProperty("重算次数")
    private Integer recalcCount;

    @Schema(description = "最后重算时间")
    @ExcelProperty("最后重算时间")
    private LocalDateTime recalcTime;

    @Schema(description = "修正指引")
    @ExcelProperty("修正指引")
    private String correctGuide;

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
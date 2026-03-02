package cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.pushrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 结果推送记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PushRecordRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29255")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "推送UUID（主键，UUID）", example = "22317")
    @ExcelProperty("推送UUID（主键，UUID）")
    private String pushId;

    @Schema(description = "推送编号")
    @ExcelProperty("推送编号")
    private String code;

    @Schema(description = "关联存档记录ID（关联eval_archive_record.archive_id）", example = "9725")
    @ExcelProperty("关联存档记录ID（关联eval_archive_record.archive_id）")
    private String archiveId;

    @Schema(description = "关联推送目标ID（关联sys_push_target.target_id）", example = "15161")
    @ExcelProperty("关联推送目标ID（关联sys_push_target.target_id）")
    private String targetId;

    @Schema(description = "关联推送方式ID（关联eval_push_type.type_id）", example = "26666")
    @ExcelProperty("关联推送方式ID（关联eval_push_type.type_id）")
    private String typeId;

    @Schema(description = "推送状态（关联sys_push_status.status_id）", example = "1")
    @ExcelProperty("推送状态（关联sys_push_status.status_id）")
    private String status;

    @Schema(description = "推送内容摘要")
    @ExcelProperty("推送内容摘要")
    private String content;

    @Schema(description = "创建人（关联sys_user.user_id）")
    @ExcelProperty("创建人（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "业务创建时间（创建时间）")
    @ExcelProperty("业务创建时间（创建时间）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "推送次数", example = "4930")
    @ExcelProperty("推送次数")
    private Integer pushCount;

    @Schema(description = "最新推送时间")
    @ExcelProperty("最新推送时间")
    private LocalDateTime latestPushTime;

    @Schema(description = "失败原因", example = "不好")
    @ExcelProperty("失败原因")
    private String failReason;

    @Schema(description = "接收方反馈状态（已反馈/未反馈/无需反馈）", example = "1")
    @ExcelProperty("接收方反馈状态（已反馈/未反馈/无需反馈）")
    private String feedbackStatus;

    @Schema(description = "数据同步量")
    @ExcelProperty("数据同步量")
    private Integer dataSyncNum;

    @Schema(description = "推送地址")
    @ExcelProperty("推送地址")
    private String targetAddr;

    @Schema(description = "待推送原因", example = "不香")
    @ExcelProperty("待推送原因")
    private String waitReason;

    @Schema(description = "待推送时长（小时）")
    @ExcelProperty("待推送时长（小时）")
    private BigDecimal waitHour;

    @Schema(description = "数据完整性校验结果（已通过/未通过）")
    @ExcelProperty("数据完整性校验结果（已通过/未通过）")
    private String dataCheckResult;

    @Schema(description = "推送内容格式（JSON/Excel/文本）")
    @ExcelProperty("推送内容格式（JSON/Excel/文本）")
    private String contentFormat;

    @Schema(description = "目标系统状态（正常/维护/异常）", example = "2")
    @ExcelProperty("目标系统状态（正常/维护/异常）")
    private String targetStatus;

    @Schema(description = "接收方响应")
    @ExcelProperty("接收方响应")
    private String receiverResp;

    @Schema(description = "重新推送次数", example = "18816")
    @ExcelProperty("重新推送次数")
    private Integer repushCount;

    @Schema(description = "最新重新推送时间")
    @ExcelProperty("最新重新推送时间")
    private LocalDateTime latestRepushTime;

    @Schema(description = "接收方反馈内容摘要")
    @ExcelProperty("接收方反馈内容摘要")
    private String feedbackContent;

    @Schema(description = "推送日志链接", example = "https://www.iocoder.cn")
    @ExcelProperty("推送日志链接")
    private String logUrl;

    @Schema(description = "数据一致性校验结果（已核对/未核对/一致/不一致）")
    @ExcelProperty("数据一致性校验结果（已核对/未核对/一致/不一致）")
    private String dataConsistResult;

    @Schema(description = "失败类型ID（关联sys_dock_fail_type.type_id）", example = "30973")
    @ExcelProperty("失败类型ID（关联sys_dock_fail_type.type_id）")
    private String failTypeId;

    @Schema(description = "目标系统错误码")
    @ExcelProperty("目标系统错误码")
    private String errorCode;

    @Schema(description = "修正方案建议")
    @ExcelProperty("修正方案建议")
    private String fixSuggest;

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
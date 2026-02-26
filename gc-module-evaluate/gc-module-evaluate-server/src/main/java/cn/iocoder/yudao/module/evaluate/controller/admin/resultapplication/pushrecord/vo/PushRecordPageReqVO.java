package cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.pushrecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 结果推送记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PushRecordPageReqVO extends PageParam {

    @Schema(description = "推送UUID（主键，UUID）", example = "22317")
    private String pushId;

    @Schema(description = "推送编号")
    private String code;

    @Schema(description = "关联存档记录ID（关联eval_archive_record.archive_id）", example = "9725")
    private String archiveId;

    @Schema(description = "关联推送目标ID（关联sys_push_target.target_id）", example = "15161")
    private String targetId;

    @Schema(description = "关联推送方式ID（关联eval_push_type.type_id）", example = "26666")
    private String typeId;

    @Schema(description = "推送状态（关联sys_push_status.status_id）", example = "1")
    private String status;

    @Schema(description = "推送内容摘要")
    private String content;

    @Schema(description = "创建人（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "业务创建时间（创建时间）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "推送次数", example = "4930")
    private Integer pushCount;

    @Schema(description = "最新推送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] latestPushTime;

    @Schema(description = "失败原因", example = "不好")
    private String failReason;

    @Schema(description = "接收方反馈状态（已反馈/未反馈/无需反馈）", example = "1")
    private String feedbackStatus;

    @Schema(description = "数据同步量")
    private Integer dataSyncNum;

    @Schema(description = "推送地址")
    private String targetAddr;

    @Schema(description = "待推送原因", example = "不香")
    private String waitReason;

    @Schema(description = "待推送时长（小时）")
    private BigDecimal waitHour;

    @Schema(description = "数据完整性校验结果（已通过/未通过）")
    private String dataCheckResult;

    @Schema(description = "推送内容格式（JSON/Excel/文本）")
    private String contentFormat;

    @Schema(description = "目标系统状态（正常/维护/异常）", example = "2")
    private String targetStatus;

    @Schema(description = "接收方响应")
    private String receiverResp;

    @Schema(description = "重新推送次数", example = "18816")
    private Integer repushCount;

    @Schema(description = "最新重新推送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] latestRepushTime;

    @Schema(description = "接收方反馈内容摘要")
    private String feedbackContent;

    @Schema(description = "推送日志链接", example = "https://www.iocoder.cn")
    private String logUrl;

    @Schema(description = "数据一致性校验结果（已核对/未核对/一致/不一致）")
    private String dataConsistResult;

    @Schema(description = "失败类型ID（关联sys_dock_fail_type.type_id）", example = "30973")
    private String failTypeId;

    @Schema(description = "目标系统错误码")
    private String errorCode;

    @Schema(description = "修正方案建议")
    private String fixSuggest;

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
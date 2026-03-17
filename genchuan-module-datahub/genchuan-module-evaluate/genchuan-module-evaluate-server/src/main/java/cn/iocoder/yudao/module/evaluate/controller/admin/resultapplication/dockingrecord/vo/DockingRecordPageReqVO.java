package cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.dockingrecord.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 系统对接记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DockingRecordPageReqVO extends PageParam {

    @Schema(description = "对接UUID（主键，UUID）", example = "31327")
    private String dockingId;

    @Schema(description = "对接编号")
    private String code;

    @Schema(description = "外部系统ID（关联sys_external_system.system_id）", example = "2722")
    private String systemId;

    @Schema(description = "对接方式ID（关联sys_docking_type.type_id）", example = "22256")
    private String typeId;

    @Schema(description = "对接状态（关联sys_docking_status.status_id）", example = "1")
    private String status;

    @Schema(description = "对接频率ID（关联sys_docking_frequency.frequency_id）", example = "29965")
    private String freqId;

    @Schema(description = "创建人（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "配置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] configTime;

    @Schema(description = "最近对接时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] latestDockTime;

    @Schema(description = "对接成功率（%）")
    private BigDecimal successRate;

    @Schema(description = "累计同步数据量")
    private Long totalSyncNum;

    @Schema(description = "失败次数", example = "23164")
    private Integer failCount;

    @Schema(description = "最新失败原因", example = "不好")
    private String latestFailReason;

    @Schema(description = "数据映射规则摘要")
    private String mapRule;

    @Schema(description = "停用操作人（关联sys_user.user_id）")
    private String stopBy;

    @Schema(description = "停用时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] stopTime;

    @Schema(description = "停用原因", example = "不好")
    private String stopReason;

    @Schema(description = "停用时长（小时）")
    private BigDecimal stopHour;

    @Schema(description = "历史对接成功率（%）")
    private BigDecimal historySuccessRate;

    @Schema(description = "配置有效性校验结果（有效/无效）")
    private String configCheckResult;

    @Schema(description = "外部系统最新状态", example = "1")
    private String externalStatus;

    @Schema(description = "重新对接次数", example = "17637")
    private Integer reDockCount;

    @Schema(description = "最新重新对接时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] latestReDockTime;

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
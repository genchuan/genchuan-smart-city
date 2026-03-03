package cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.dockingrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 系统对接记录新增/修改 Request VO")
@Data
public class DockingRecordSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13567")
    private Long id;

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
    private LocalDateTime configTime;

    @Schema(description = "最近对接时间")
    private LocalDateTime latestDockTime;

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
    private LocalDateTime stopTime;

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
    private LocalDateTime latestReDockTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
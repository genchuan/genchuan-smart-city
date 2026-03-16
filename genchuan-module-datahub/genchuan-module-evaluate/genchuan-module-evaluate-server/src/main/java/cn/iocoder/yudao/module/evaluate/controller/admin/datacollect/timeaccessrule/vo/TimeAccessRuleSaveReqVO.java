package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.timeaccessrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 实时接入规则新增/修改 Request VO")
@Data
public class TimeAccessRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14294")
    private Long id;

    @Schema(description = "规则UUID", example = "4028")
    private String ruleId;

    @Schema(description = "规则名称", example = "张三")
    private String name;

    @Schema(description = "规则编码")
    private String code;

    @Schema(description = "关联评价任务ID", example = "17020")
    private String taskId;

    @Schema(description = "关联指标项ID", example = "7147")
    private String indexId;

    @Schema(description = "数据来源设备ID", example = "6528")
    private String deviceId;

    @Schema(description = "同步频率ID", example = "9249")
    private String syncFreqId;

    @Schema(description = "数据清洗规则")
    private String cleanRule;

    @Schema(description = "状态", example = "2")
    private Long status;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "更新人")
    private String updateBy;

    @Schema(description = "业务创建时间（原create_time）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "业务更新时间（原update_time）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "最近同步时间")
    private LocalDateTime lastSyncTime;

    @Schema(description = "同步成功率")
    private BigDecimal syncSuccessRate;

    @Schema(description = "今日同步次数", example = "13020")
    private Integer todaySyncCount;

    @Schema(description = "累计同步次数", example = "15562")
    private Long totalSyncCount;

    @Schema(description = "停用原因", example = "不喜欢")
    private String stopReason;

    @Schema(description = "停用时间")
    private LocalDateTime stopTime;

    @Schema(description = "停用操作人")
    private String stopBy;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
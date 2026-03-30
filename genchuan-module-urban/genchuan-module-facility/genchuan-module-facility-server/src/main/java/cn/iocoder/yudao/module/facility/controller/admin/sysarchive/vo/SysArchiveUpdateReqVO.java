package cn.iocoder.yudao.module.facility.controller.admin.sysarchive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 归档新增/修改 Request VO")
@Data
public class SysArchiveUpdateReqVO {

    @Schema(description = "[主键ID] 主键，归档记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "30650")
    private Long id;

    @Schema(description = "[归档编号] 归档唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[归档编号] 归档唯一编号不能为空")
    private String archiveNo;

    @Schema(description = "[工单编号] 关联工单编号")
    private String orderNo;

    @Schema(description = "[工单类型] 工单类型", example = "1")
    private String orderType;

    @Schema(description = "[工单处置类型] 工单处置类型，工单获取", example = "2")
    private String bizType;

    @Schema(description = "[工单完成时间] 工单完成时间")
    private LocalDateTime completeTime;

    @Schema(description = "[预警编号] 预警编号")
    private String warnNo;

    @Schema(description = "[设施编码] 关联对应设施表编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[设施编码] 关联对应设施表编码不能为空")
    private String facilityCode;

    @Schema(description = "[设施名称] 设施名称", example = "张三")
    private String facilityName;

    @Schema(description = "[设施类型] 设施类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[设施类型] 设施类型不能为空")
    private String facilityType;

    @Schema(description = "[指派运维员ID] 指派运维员id，工单获取", example = "17090")
    private Long assignStaffId;

    @Schema(description = "[指派运维员姓名] 指派运维员name，工单获取", example = "张三")
    private String assignStaffName;

    @Schema(description = "[核查人ID] 核查人id", example = "8926")
    private Long checkStaffId;

    @Schema(description = "[核查人姓名] 核查人name", example = "李四")
    private String checkStaffName;

    @Schema(description = "[所属区域12位编码] 所属区域12位编码，工单获取")
    private String areaFullCode;

    @Schema(description = "[所属区域名称] 所属区域名称，工单获取", example = "李四")
    private String areaName;

    @Schema(description = "[处理时间] 处理时间，预警产生时间到工单完成时间，单位分钟")
    private Integer dealDuration;

    @Schema(description = "[核查结果]如：达标/未达标/待复核/数据异常")
    private String checkResult;

    @Schema(description = "[核查意见] 核查意见")
    private String checkSuggest;

    @Schema(description = "[归档资料数] 归档资料数，数值，从工单统计")
    private Double fileNum;

    @Schema(description = "[超标指标名称] 超标指标名称（处置前），预警得到", example = "李四")
    private String overIndexName;

    @Schema(description = "[处置前指标值] 处置前指标值，预警得到")
    private BigDecimal beforeIndexValue;

    @Schema(description = "[处置后指标值] 处置后指标值，工单得到")
    private BigDecimal afterIndexValue;

    @Schema(description = "[恢复值] = 处置前指标值 - 处置后指标值")
    private BigDecimal recoverValue;

    @Schema(description = "[指标阈值] 指标阈值，预警得到")
    private BigDecimal thresholdValue;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}

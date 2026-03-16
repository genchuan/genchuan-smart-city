package cn.iocoder.yudao.module.facility.controller.admin.workorder.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkOrderPageReqVO extends PageParam {

    @Schema(description = "[所属设施类型] 如：道路", example = "1")
    private String facilityType;

    @Schema(description = "[工单编号] 工单唯一编号")
    private String orderNo;

    @Schema(description = "[关联预警ID] 关联的预警记录ID", example = "9822")
    private Long warnId;

    @Schema(description = "[关联预警编号] 关联的预警编号")
    private String warnNo;

    @Schema(description = "[关联设施ID] 关联的设施ID", example = "18643")
    private Long facilityId;

    @Schema(description = "[关联设施名称] 关联的设施名称（冗余）", example = "王五")
    private String facilityName;

    @Schema(description = "[指派运维员ID] 指派处理该工单的运维人员ID", example = "28535")
    private Long assignStaffId;

    @Schema(description = "[指派运维员名称] 指派运维员姓名（冗余）", example = "李四")
    private String assignStaffName;

    @Schema(description = "[工单类型] 运维/养护/维修/清淤/巡检/处置", example = "1")
    private String orderType;

    @Schema(description = "[业务类型] 业务类型，具体取值依赖于工单类型：当order_type为dredge_order时取机械清淤/人工清淤/高压冲洗；当order_type为disposal_order时取倾斜/振动/开合异常；其他情况取值与工单类型相同或业务定义", example = "2")
    private String bizType;

    @Schema(description = "[处置时限] 处置时限，单位：小时")
    private BigDecimal dealLimit;

    @Schema(description = "[抵达现场时间] 运维人员抵达现场的时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] arriveTime;

    @Schema(description = "[提醒时间] 点击“确认”后向所选工单的运维员发送待办提醒，记录提醒时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] remindTime;

    @Schema(description = "[工单完成时间] 工单完成的日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] completeTime;

    @Schema(description = "[所属区域名称] 工单所属区域名称", example = "芋艿")
    private String areaName;

    @Schema(description = "[所属区域12位全码] 所属区域12位全码（GB/T 2260），到社区级")
    private String areaFullCode;

    @Schema(description = "[工单优先等级] 工单优先等级，如：3-高/2-中/1-低")
    private Integer priorityLevel;

    @Schema(description = "[安全风险等级] 安全风险等级，如：3-高/2-中/1-低")
    private Integer riskLevel;

    @Schema(description = "[当前处置进度] 当前处置进度：待处置/处置中/处置完成/待核查/已完成", example = "1")
    private String processStatus;

    @Schema(description = "[录入进度说明] 录入的进度说明文本")
    private String processDesc;

    @Schema(description = "[处理情况说明] 处理情况说明，如处置措施、故障排查结果、巡检内容等")
    private String dealContent;

    @Schema(description = "[督办意见] 督办意见")
    private String superviseOpinion;

    @Schema(description = "[现场检测数据url列表字符串] 现场检测数据URL列表字符串varchar")
    private String siteDataUrlListStr;

    @Schema(description = "[录入资料说明] 录入资料说明")
    private String fileDesc;

    @Schema(description = "[处理后的指标数值]",example = "10")
    private BigDecimal afterIndexValue;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}

package cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 通用预警分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysWarnPageReqVO extends PageParam {

    @Schema(description = "[预警编号] 预警唯一编号")
    private String warnNo;

    @Schema(description = "[设施ID] 关联设施表ID", example = "29359")
    private Long facilityId;

    @Schema(description = "[设施名称] 设施名称", example = "张三")
    private String facilityName;

    @Schema(description = "[设施唯一code] 设施唯一编码")
    private String facilityCode;

    @Schema(description = "[工单ID] 关联工单ID", example = "10560")
    private Long workOrderId;

    @Schema(description = "[工单唯一code] 工单唯一编码")
    private String workOrderCode;

    @Schema(description = "[监测设备ID] 关联监测设备ID", example = "16520")
    private Long deviceId;

    @Schema(description = "[监测设备唯一code] 监测设备唯一编码")
    private String deviceCode;

    @Schema(description = "[监测实时数据ID] 关联监测实时数据ID", example = "31788")
    private Long monitorId;

    @Schema(description = "[监测实时数据唯一code] 监测实时数据唯一编码")
    private String monitorCode;

    @Schema(description = "[预警状态] 如：待处置/有效待派单/已派单/超时/无效已归档", example = "1")
    private String status;

    @Schema(description = "[派单状态] 如：未派单/已派单", example = "2")
    private String assignStatus;

    @Schema(description = "[所属设施类型] 如：道路", example = "2")
    private String facilityType;

    @Schema(description = "[预警类型] 如：坑洼数量超标/裂缝长度超标/路面温度超标/交通流量超标", example = "1")
    private String type;

    @Schema(description = "[预警方式] 如：自动监测/人工上报", example = "2")
    private String wayType;

    @Schema(description = "[预警等级] 如：1-一般/2-较重/3-严重/4-紧急")
    private Integer level;

    @Schema(description = "[触发时间] 预警触发时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] triggerTime;

    @Schema(description = "[预警处置时限] 单位：小时，可小数")
    private BigDecimal dealLimit;

    @Schema(description = "[超标指标名称] 如坑洼数量、裂缝长度等")
    private String overIndex;

    @Schema(description = "[超标数值] 实际超标的数值")
    private BigDecimal overValue;

    @Schema(description = "[超标阈值数值] 阈值")
    private BigDecimal thresholdValue;

    @Schema(description = "[确认意见] 人工确认后的描述")
    private String confirmOpinion;

    @Schema(description = "[无效原因] 如设备故障/数据波动/人为误触等", example = "不喜欢")
    private String invalidReason;

    @Schema(description = "[处理建议] 系统或人工给出的处置建议")
    private String suggest;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}

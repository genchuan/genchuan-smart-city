package cn.iocoder.yudao.module.facility.dal.dataobject.road.roadwarn;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 预警 DO
 *
 * @author 亘川智城
 */
@TableName("sys_warn")
@KeySequence("sys_warn_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadWarnDO extends BaseDO {

    /**
     * [主键ID] 主键，预警记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [预警编号] 预警唯一编号
     */
    private String warnNo;
    /**
     * [设施ID] 关联设施表ID
     */
    private Long facilityId;
    /**
     * [设施名称] 设施名称
     */
    private String facilityName;
    /**
     * [设施唯一code] 设施唯一编码
     */
    private String facilityCode;
    /**
     * [工单ID] 关联工单ID
     */
    private Long workOrderId;
    /**
     * [工单唯一code] 工单唯一编码
     */
    private String workOrderCode;
    /**
     * [监测设备ID] 关联监测设备ID
     */
    private Long deviceId;
    /**
     * [监测设备唯一code] 监测设备唯一编码
     */
    private String deviceCode;
    /**
     * [监测实时数据ID] 关联监测实时数据ID
     */
    private Long monitorId;
    /**
     * [监测实时数据唯一code] 监测实时数据唯一编码
     */
    private String monitorCode;
    /**
     * [预警状态] 如：待处置/有效待派单/已派单/超时/无效已归档
     */
    private String status;
    /**
     * [派单状态] 如：未派单/已派单
     */
    private String assignStatus;
    /**
     * [所属设施类型] 如：道路
     */
    private String facilityType;
    /**
     * [预警类型] 如：坑洼数量超标/裂缝长度超标/路面温度超标/交通流量超标
     */
    private String type;
    /**
     * [预警方式] 如：自动监测/人工上报
     */
    private String wayType;
    /**
     * [预警等级] 如：1-一般/2-较重/3-严重/4-紧急
     */
    private Integer level;
    /**
     * [触发时间] 预警触发时间
     */
    private LocalDateTime triggerTime;
    /**
     * [预警处置时限] 单位：小时，可小数
     */
    private BigDecimal dealLimit;
    /**
     * [超标指标名称] 如坑洼数量、裂缝长度等
     */
    private String overIndex;
    /**
     * [超标数值] 实际超标的数值
     */
    private BigDecimal overValue;
    /**
     * [超标阈值数值] 阈值
     */
    private BigDecimal thresholdValue;
    /**
     * [确认意见] 人工确认后的描述
     */
    private String confirmOpinion;
    /**
     * [无效原因] 如设备故障/数据波动/人为误触等
     */
    private String invalidReason;
    /**
     * [处理建议] 系统或人工给出的处置建议
     */
    private String suggest;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}

package cn.iocoder.yudao.module.facility.dal.dataobject.sysarchive;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 归档 DO
 *
 * @author 亘川智城
 */
@TableName("sys_archive")
@KeySequence("sys_archive_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysArchiveDO extends BaseDO {

    /**
     * [主键ID] 主键，归档记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [归档编号] 归档唯一编号
     */
    private String archiveNo;
    /**
     * [工单编号] 关联工单编号
     */
    private String orderNo;
    /**
     * [工单类型] 工单类型
     */
    private String orderType;
    /**
     * [工单处置类型] 工单处置类型，工单获取
     */
    private String bizType;
    /**
     * [工单完成时间] 工单完成时间
     */
    private LocalDateTime completeTime;
    /**
     * [预警编号] 预警编号
     */
    private String warnNo;
    /**
     * [设施编码] 关联对应设施表编码
     */
    private String facilityCode;
    /**
     * [设施名称] 设施名称
     */
    private String facilityName;
    /**
     * [设施类型] 设施类型
     */
    private String facilityType;
    /**
     * [指派运维员ID] 指派运维员id，工单获取
     */
    private Long assignStaffId;
    /**
     * [指派运维员姓名] 指派运维员name，工单获取
     */
    private String assignStaffName;
    /**
     * [核查人ID] 核查人id
     */
    private Long checkStaffId;
    /**
     * [核查人姓名] 核查人name
     */
    private String checkStaffName;
    /**
     * [所属区域12位编码] 所属区域12位编码，工单获取
     */
    private String areaFullCode;
    /**
     * [所属区域名称] 所属区域名称，工单获取
     */
    private String areaName;
    /**
     * [处理时间] 处理时间，预警产生时间到工单完成时间，单位分钟
     */
    private Integer dealDuration;
    /**
     * [核查结果]如：达标/未达标/待复核/数据异常
     */
    private String checkResult;
    /**
     * [核查意见] 核查意见
     */
    private String checkSuggest;
    /**
     * [归档资料数] 归档资料数，数值，从工单统计
     */
    private Double fileNum;
    /**
     * [超标指标名称] 超标指标名称（处置前），预警得到
     */
    private String overIndexName;
    /**
     * [处置前指标值] 处置前指标值，预警得到
     */
    private BigDecimal beforeIndexValue;
    /**
     * [处置后指标值] 处置后指标值，工单得到
     */
    private BigDecimal afterIndexValue;
    /**
     * [恢复值] = 处置前指标值 - 处置后指标值
     */
    private BigDecimal recoverValue;
    /**
     * [指标阈值] 指标阈值，预警得到
     */
    private BigDecimal thresholdValue;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;

}

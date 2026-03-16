package cn.iocoder.yudao.module.facility.dal.dataobject.workorder;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工单 DO
 *
 * @author 亘川智城
 */
@TableName("work_order")
@KeySequence("work_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderDO extends BaseDO {

    /**
     * [工单ID] 主键，工单唯一标识
     */
    @TableId
    private Long id;
    /**
     * [所属设施类型] 如：道路
     */
    private String facilityType;
    /**
     * [工单编号] 工单唯一编号
     */
    private String orderNo;
    /**
     * [关联预警ID] 关联的预警记录ID
     */
    private Long warnId;
    /**
     * [关联预警编号] 关联的预警编号
     */
    private String warnNo;
    /**
     * [关联设施ID] 关联的设施ID
     */
    private Long facilityId;
    /**
     * [关联设施名称] 关联的设施名称（冗余）
     */
    private String facilityName;
    /**
     * [指派运维员ID] 指派处理该工单的运维人员ID
     */
    private Long assignStaffId;
    /**
     * [指派运维员名称] 指派运维员姓名（冗余）
     */
    private String assignStaffName;
    /**
     * [工单类型] 运维/养护/维修/清淤/巡检/处置
     */
    private String orderType;
    /**
     * [业务类型] 业务类型，具体取值依赖于工单类型：当order_type为dredge_order时取机械清淤/人工清淤/高压冲洗；当order_type为disposal_order时取倾斜/振动/开合异常；其他情况取值与工单类型相同或业务定义
     */
    private String bizType;
    /**
     * [处置时限] 处置时限，单位：小时
     */
    private BigDecimal dealLimit;
    /**
     * [抵达现场时间] 运维人员抵达现场的时间
     */
    private LocalDateTime arriveTime;
    /**
     * [提醒时间] 点击“确认”后向所选工单的运维员发送待办提醒，记录提醒时间
     */
    private LocalDateTime remindTime;
    /**
     * [工单完成时间] 工单完成的日期
     */
    private LocalDateTime completeTime;
    /**
     * [所属区域名称] 工单所属区域名称
     */
    private String areaName;
    /**
     * [所属区域12位全码] 所属区域12位全码（GB/T 2260），到社区级
     */
    private String areaFullCode;
    /**
     * [工单优先等级] 工单优先等级，如：3-高/2-中/1-低
     */
    private Integer priorityLevel;
    /**
     * [安全风险等级] 安全风险等级，如：3-高/2-中/1-低
     */
    private Integer riskLevel;
    /**
     * [当前处置进度] 当前处置进度：待处置/处置中/处置完成/待核查/已完成
     */
    private String processStatus;
    /**
     * [录入进度说明] 录入的进度说明文本
     */
    private String processDesc;

    /**
     * [处理情况说明] 处理情况说明，如处置措施、故障排查结果、巡检内容等
     */
    private String dealContent;

    /**
     * [督办意见] 督办意见
     */
    private String superviseOpinion;
    /**
     * [现场检测数据url列表字符串] 现场检测数据URL列表字符串varchar
     */
    private String siteDataUrlListStr;
    /**
     * [录入资料说明] 录入资料说明
     */
    private String fileDesc;

    /**
     * [处理后的指标数值]
     */
    private BigDecimal afterIndexValue;

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

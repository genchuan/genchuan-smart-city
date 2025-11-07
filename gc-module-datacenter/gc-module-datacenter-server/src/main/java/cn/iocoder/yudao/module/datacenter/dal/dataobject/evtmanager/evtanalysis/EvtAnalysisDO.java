package cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtanalysis;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件研判分析 DO
 *
 * @author 亘川智城
 */
@TableName("biz_evt_analysis")
@KeySequence("biz_evt_analysis_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvtAnalysisDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 研判ID，唯一编码，UUID
     */
    private String analysisId;
    /**
     * 事件编码，关联事件接报登记表
     */
    private String evtCode;
    /**
     * 事件名称，拼接“类型名称+事件名称”，如“交通拥堵 - XX路”
     */
    private String evtName;
    /**
     * 研判人ID，研判分析师账号，关联用户信息表(sys_user)
     */
    private String analystId;
    /**
     * 研判人姓名，与研判人ID同步
     */
    private String analystName;
    /**
     * 研判时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成
     */
    private LocalDateTime analysisTime;
    /**
     * 事件原因，研判的事件原因，如“交通事故导致XX路拥堵”
     */
    private String evtReason;
    /**
     * 影响范围，描述影响范围，如“XX路1公里内，涉及2个小区”
     */
    private String impactRange;
    /**
     * 紧急程度，一般/紧急/特急，研判确定的紧急等级
     */
    private String urgencyLevel;
    /**
     * 研判结论，处置建议，如“优先调度交警处置事故，疏导车流”
     */
    private String conclusion;
    /**
     * 关联监测数据ID，关联监测部件数据表，如摄像头数据
     */
    private String relMonDataId;
    /**
     * 监测数据摘要，监测数据简要描述，如“XX摄像头显示20辆车辆拥堵”
     */
    private String monDataAbstract;
    /**
     * 是否需要分拨，1（是）/0（否），标识是否需后续分拨
     */
    private String needAllocate;

}
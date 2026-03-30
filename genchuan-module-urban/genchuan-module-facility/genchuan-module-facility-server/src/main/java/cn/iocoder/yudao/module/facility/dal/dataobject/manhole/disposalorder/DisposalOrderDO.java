package cn.iocoder.yudao.module.facility.dal.dataobject.manhole.disposalorder;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import groovy.transform.EqualsAndHashCode;
import lombok.*;

import java.time.LocalDate;

/**
 * 处置工单 DO
 *
 * @author 亘川智城
 */
@TableName("disposal_order")
@KeySequence("disposal_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisposalOrderDO extends BaseDO {

    /**
     * 主键（自增）
     */
    @TableId
    private Long id;
    /**
     * 关联告警表sys_warn的warn_id
     */
    private Long warnId;
    /**
     * 关联窨井盖表manhole_cover的id
     */
    private Long coverId;
    /**
     * 异常类型：倾斜/振动/开合异常
     */
    private String abnormalType;
    /**
     * 关联风险等级表sys_risk_level的id
     */
    private Long riskLevelId;
    /**
     * 关联用户表sys_user的id（指派运维员）
     */
    private Long assignStaffId;
    /**
     * 处置时限（日期）
     */
    private LocalDate dealLimit;
    /**
     * 处置进度：待处置/现场处置/处置中/已完成
     */
    private String processStatus;
    /**
     * 工单完成时间（日期）
     */
    private LocalDate completeTime;
    /**
     * [通用扩展字段1] 预留
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 预留
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 预留
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 预留
     */
    private String extCommon4;

}

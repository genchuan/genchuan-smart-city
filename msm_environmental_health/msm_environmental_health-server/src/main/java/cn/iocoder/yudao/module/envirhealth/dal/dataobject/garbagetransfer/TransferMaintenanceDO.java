package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer;

import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备维护 DO
 *
 * @author 芋道源码
 */
@TableName("garbage_transfer_maintenance")
@KeySequence("garbage_transfer_maintenance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferMaintenanceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 维护主键（UUID）
     */
    private String maintenanceId;
    /**
     * 关联garbage_transfer.transfer_id
     */
    private String transferId;
    /**
     * 关联sys_equipment.id
     */
    private String equipmentId;
    /**
     * 维护周期
     */
    private String maintenanceCycle;
    /**
     * 上次维护时间
     */
    private LocalDateTime lastMaintenanceTime;
    /**
     * 维护内容
     */
    private String maintenanceContent;
    /**
     * 关联sys_user.id
     */
    private String handleBy;
    /**
     * 维护状态：待维护/维护中/已完成
     */
    private String maintenanceStatus;
    /**
     * 预计完成时间
     */
    private LocalDateTime expectedCompleteTime;
    /**
     * 超时提醒：是/否
     */
    private String abnormalIsTimeout;
    /**
     * 维护时间
     */
    private LocalDateTime maintenanceTime;
    /**
     * 更换配件
     */
    private String replaceParts;
    /**
     * 维护费用（单位：元）
     */
    private BigDecimal maintenanceCost;
    /**
     * 维护照片URL，JSON
     */
    private String maintenancePhoto;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}
package cn.iocoder.yudao.module.waterdetection.dal.dataobject.consumablemanagement;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 耗材库存与更换管理 DO
 *
 * @author zcq
 */
@TableName("gc_consumable_management")
@KeySequence("gc_consumable_management_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumableManagementDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 耗材ID
     */
    private String consumableId;
    /**
     * 耗材类型
     */
    private String consumableType;
    /**
     * 库存余量
     */
    private Double stockQuantity;
    /**
     * 预警阈值
     */
    private Double warningThreshold;
    /**
     * 上次更换日期
     */
    private LocalDateTime lastReplacementDate;
    /**
     * 预计下次更换日期
     */
    private LocalDateTime nextReplacementDate;
    /**
     * 更换数量
     */
    private Double replacementQuantity;
    /**
     * 关联设备ID
     */
    private String relatedEquipmentId;

}
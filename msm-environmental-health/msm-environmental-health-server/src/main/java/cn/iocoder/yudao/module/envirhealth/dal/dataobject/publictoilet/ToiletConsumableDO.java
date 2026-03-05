package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 公厕耗材配置 DO
 *
 * @author 亘川智城
 */
@TableName("public_toilet_consumable")
@KeySequence("public_toilet_consumable_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToiletConsumableDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 公厕ID，关联public_toilet.id
     */
    private String toiletId;
    /**
     * 耗材ID，关联sys_consumable.id
     */
    private String consumableId;
    /**
     * 当前库存数量
     */
    private Integer consumableStock;
    /**
     * 预警阈值
     */
    private Integer consumableThreshold;
    /**
     * 预警状态：正常/预警/严重预警
     */
    private String consumableWarning;
    /**
     * 上次补充时间
     */
    private LocalDateTime lastSupplyTime;
    /**
     * 补充周期，单位：天
     */
    private Integer supplyCycle;
    /**
     * 缺口数量
     */
    private Integer consumableGap;
    /**
     * 负责人ID，关联sys_user.id，负责该耗材的管理和补充
     */
    private String managerId;

}
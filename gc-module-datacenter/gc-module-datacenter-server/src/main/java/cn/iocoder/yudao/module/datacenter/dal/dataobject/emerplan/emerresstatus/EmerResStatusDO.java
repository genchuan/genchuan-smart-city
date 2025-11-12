package cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerresstatus;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资源状态统计 DO
 *
 * @author 亘川智城
 */
@TableName("stat_emer_res_status")
@KeySequence("stat_emer_res_status_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmerResStatusDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String statId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 统计时间
     */
    private LocalDateTime statTime;
    /**
     * 资源类型
     */
    private String resType;
    /**
     * 行政区划代码
     */
    private String regionCode;
    /**
     * 行政区划名称
     */
    private String regionName;
    /**
     * 总数量
     */
    private Integer totalQuantity;
    /**
     * 可用数量
     */
    private Integer availableQuantity;
    /**
     * 异常数量
     */
    private Integer abnQuantity;

}
package cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkrealtime;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车泊位实时状态 DO
 *
 * @author lxs
 */
@TableName("biz_park_real_time")
@KeySequence("biz_park_real_time_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkRealTimeDO extends BaseDO {

    /**
     * 主键，唯一标识
     */
    @TableId
    private Long id;
    /**
     * 推荐停车场唯一标识
     */
    private String recommendParkId;
    /**
     * 当前剩余可用泊位数量
     */
    private Integer freeSpace;
    /**
     * 泊位占用比例（百分比）
     */
    private BigDecimal occupyRate;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}

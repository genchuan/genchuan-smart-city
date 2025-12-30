package cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkwo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车订单 DO
 *
 * @author lxs
 */
@TableName("biz_park_wo")
@KeySequence("biz_park_wo_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkWoDO extends BaseDO {

    /**
     * 主键ID，唯一标识
     */
    @TableId
    private Long id;
//    /**
//     * 订单ID，唯一标识
//     */
//    private String woId;
    /**
     * 订单编号
     */
    private String woNo;

    /**
     * 停车场id
     */
    private Integer parkId;
    /**
     * 停车场名称
     */
    private String parkName;
    /**
     * 停车时长（分钟）
     */
    private Integer parkEndure;
    /**
     * 应收金额
     */
    private BigDecimal receivableAmount;
    /**
     * 欠费原因说明
     */
    private String arrearsReason;
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

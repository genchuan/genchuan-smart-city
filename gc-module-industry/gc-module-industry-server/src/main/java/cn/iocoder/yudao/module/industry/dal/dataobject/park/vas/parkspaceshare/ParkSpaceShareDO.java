package cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkspaceshare;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车位共享配置 DO
 *
 * @author lxs
 */
@TableName("park_space_share")
@KeySequence("park_space_share_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkSpaceShareDO extends BaseDO {

    /**
     * [主键ID] 车位共享配置唯一标识
     */
    @TableId
    private Long id;
    /**
     * [共享编号] 车位共享业务编号
     */
    private String shareNo;
    /**
     * [车位ID] 共享车位ID
     */
    private Long spaceId;
    /**
     * [车位所有人ID] 车位所有人ID
     */
    private Long userId;
    /**
     * [每小时价格] 车位共享每小时费用
     */
    private BigDecimal pricePerHour;
    /**
     * [可预约最大时长] 单次可预约的最大时长（小时）
     */
    private Integer maxBookingDuration;
    /**
     * [共享生效时间] 车位共享生效开始时间
     */
    private LocalDateTime startTime;
    /**
     * [共享失效时间] 车位共享失效结束时间
     */
    private LocalDateTime endTime;
    /**
     * [状态] 如：禁用/启用/暂停
     */
    private String status;
    /**
     * [共享订单数] 车位共享产生的订单数量
     */
    private Integer orderCount;
    /**
     * [共享收益总额] 车位共享累计收益金额
     */
    private BigDecimal incomeAmount;
    /**
     * [备注] 车位共享相关备注说明
     */
    private String remark;
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

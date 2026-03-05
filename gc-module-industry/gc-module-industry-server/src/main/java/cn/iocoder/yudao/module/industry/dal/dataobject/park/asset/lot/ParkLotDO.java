package cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.lot;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车场信息 DO
 *
 * @author zhucongquan
 */
@TableName("park_lot")
@KeySequence("park_lot_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkLotDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联ID
     */
    private String assetExtendId;
    /**
     * 总车位数
     */
    private Integer totalSpace;
    /**
     * 当前可用车位数
     */
    private Integer availableSpace;
    /**
     * 车场类型
     */
    private String parkType;
    /**
     * 开放时间
     */
    private LocalTime openTime;
    /**
     * 关闭时间
     */
    private LocalTime closeTime;
    /**
     * 运营商户ID
     */
    private String managementMerchantId;
    /**
     * 默认费率策略ID
     */
    private String feeStrategyId;
    /**
     * 业务创建时间
     */
    private LocalDateTime lotCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime lotUpdateTime;
    /**
     * 业务备注
     */
    private String lotRemark;

}
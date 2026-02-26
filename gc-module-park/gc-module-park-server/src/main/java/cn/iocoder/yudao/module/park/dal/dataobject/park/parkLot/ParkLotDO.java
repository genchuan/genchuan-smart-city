package cn.iocoder.yudao.module.park.dal.dataobject.park.parkLot;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;


@TableName("park_lot")
@KeySequence("park_lot") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkLotDO extends BaseDO{
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO) // 表用自增ID，对应 AUTO_INCREMENT
    private Long id;

    /**
     * 车场ID（UUID）
     */
    private String lotId;

    /**
     * 关联资产扩展ID
     */
    private Long assetExtendId;

    /**
     * 12位地区码
     */
    private String regionFullCode;

    /**
     * 总车位数
     */
    private Integer totalSpace;

    /**
     * 当前可用车位数
     */
    private Integer availableSpace;

    /**
     * 车场类型：地面/地下/立体/路侧
     */
    private String parkType;

    /**
     * 开放时间
     */
    private String openTime;

    /**
     * 关闭时间，24小时为NULL
     */
    private String closeTime;

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

    /**
     * 创建者
     */
    private String creator;

    /**
     * 更新者
     */
    private String updater;

    /**
     * 删除标识（bit类型，0-未删，1-已删）
     */
    @TableLogic(value = "0", delval = "1") // 适配bit类型的逻辑删除
    private Boolean deleted;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 创建时间（数据库自动填充）
     */
    private LocalDateTime createTime;

    /**
     * 更新时间（数据库自动更新）
     */
    private LocalDateTime updateTime;
}

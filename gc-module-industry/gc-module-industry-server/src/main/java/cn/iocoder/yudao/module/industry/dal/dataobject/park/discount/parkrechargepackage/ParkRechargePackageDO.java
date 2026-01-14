package cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkrechargepackage;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 充值套餐 DO
 *
 * @author lxs
 */
@TableName("park_recharge_package")
@KeySequence("park_recharge_package_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkRechargePackageDO extends BaseDO {

    /**
     * [主键ID] 充值套餐唯一标识
     */
    @TableId
    private Long id;
    /**
     * [套餐名称] 充值套餐名称
     */
    private String packageName;
    /**
     * [充值金额] 实际充值金额
     */
    private BigDecimal rechargeAmount;
    /**
     * [赠送金额] 充值赠送金额
     */
    private BigDecimal giveAmount;
    /**
     * [赠送时间] 赠送时间，单位分钟
     */
    private Integer giveTime;
    /**
     * [状态] 如:上架/下架
     */
    private String status;
    /**
     * [销售数量] 套餐销售数量
     */
    private Integer salesCount;
    /**
     * [备注] 充值套餐相关备注说明
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

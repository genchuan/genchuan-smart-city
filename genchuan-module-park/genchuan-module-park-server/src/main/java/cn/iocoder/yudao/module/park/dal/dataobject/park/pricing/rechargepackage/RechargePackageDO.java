package cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.rechargepackage;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 充值套餐 DO
 *
 * @author 亘川智城
 */
@TableName("park_recharge_package")
@KeySequence("park_recharge_package_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RechargePackageDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [套餐名称]
     */
    private String packageName;
    /**
     * [充值金额]
     */
    private BigDecimal rechargeAmount;
    /**
     * [赠送金额/时长] JSON格式varchar
     */
    private String giveAmount;
    /**
     * [赠送内容有效期] 可为NULL
     */
    private Integer validDays;
    /**
     * [套餐类型] 如:金额套餐/时长套餐
     */
    private String packageType;
    /**
     * [状态] 如:上架/下架
     */
    private String status;
    /**
     * [销量]
     */
    private Integer salesCount;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}

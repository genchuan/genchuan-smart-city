package cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantrecharge;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商户充值 DO
 *
 * @author 亘川智城
 */
@TableName("merchant_recharge")
@KeySequence("merchant_recharge_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantRechargeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 商户ID，关联merchant_info.id
     */
    private Long merchantId;
    /**
     * 商户名称
     */
    @TableField(exist = false)
    private String merchantName;
    /**
     * 充值金额
     */
    private BigDecimal amount;
    /**
     * 支付渠道：微信/支付宝/银行转账/平台余额
     */
    private String payChannel;
    /**
     * 充值状态：待支付/已支付/已取消
     */
    private String status;
    /**
     * 充值订单号，唯一
     */
    private String orderNo;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 确认时间
     */
    private LocalDateTime confirmTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
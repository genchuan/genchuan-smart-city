package cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantinfo;

import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商户信息 DO
 *
 * @author 亘川智城
 */
@TableName("merchant_info")
@KeySequence("merchant_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 商户名称，唯一
     */
    private String name;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系手机号
     */
    private String phone;
    /**
     * 商户类型：充电商户/停车商户/充停一体商户
     */
    private String merchantType;
    /**
     * 商户地址
     */
    private String address;
    /**
     * 注册时间
     */
    private LocalDateTime registerTime;
    /**
     * 商户状态：待审核/正常/禁用/已驳回
     */
    private String status;
    /**
     * 账户余额
     */
    private BigDecimal walletBalance;
    /**
     * 审核人ID
     */
    private Long auditorId;
    /**
     * 审核人名称
     */
    @TableField(exist = false)
    private String auditorName;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
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
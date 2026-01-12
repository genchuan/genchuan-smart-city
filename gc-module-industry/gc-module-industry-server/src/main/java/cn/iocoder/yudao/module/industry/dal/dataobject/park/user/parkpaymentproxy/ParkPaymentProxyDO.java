package cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkpaymentproxy;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 代付规则 DO
 *
 * @author lxs
 */
@TableName("park_payment_proxy")
@KeySequence("park_payment_proxy_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkPaymentProxyDO extends BaseDO {

    /**
     * [主键ID] 代付规则唯一标识
     */
    @TableId
    private Long id;
    /**
     * [规则名称] 代付规则名称
     */
    private String proxyName;
    /**
     * [代付类型] 如：企业代付/政府代付/指定用户代付
     */
    private String proxyType;
    /**
     * [付款方ID] 可为用户ID或商户ID
     */
    private Long payerId;
    /**
     * [收款方类型] 如：用户/商户
     */
    private String payeeType;
    /**
     * [适用资源ID列表] JSON 格式，varchar 存储
     */
    private String assetIds;
    /**
     * [状态] 如：启用/禁用
     */
    private String status;
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
    /**
     * [备注] 代付规则相关备注说明
     */
    private String remark;

}

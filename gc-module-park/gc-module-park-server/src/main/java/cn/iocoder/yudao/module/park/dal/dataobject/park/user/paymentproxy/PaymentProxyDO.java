package cn.iocoder.yudao.module.park.dal.dataobject.park.user.paymentproxy;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 代付规则 DO
 *
 * @author 亘川智城
 */
@TableName("park_payment_proxy")
@KeySequence("park_payment_proxy_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProxyDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [规则名称]
     */
    private String proxyName;
    /**
     * [代付类型] 如:企业代付/政府代付/其他
     */
    private String proxyType;
    /**
     * [付款方ID] 关联park_user.id/park_enterprise_information.enterprise_id
     */
    private Long payerId;
    /**
     * [收款方类型] 如:商户/平台
     */
    private String payeeType;
    /**
     * [收款方ID] 关联park_merchant.merchant_id
     */
    private Long payeeId;
    /**
     * [适用资源ID列表] JSON格式varchar，关联tb_asset_extend.asset_extend_id
     */
    private String assetIds;
    /**
     * [状态] 如:启用/禁用
     */
    private String status;
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

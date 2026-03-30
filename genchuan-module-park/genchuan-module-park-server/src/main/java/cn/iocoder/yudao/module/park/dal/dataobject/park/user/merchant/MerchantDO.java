package cn.iocoder.yudao.module.park.dal.dataobject.park.user.merchant;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 商户 DO
 *
 * @author 亘川智城
 */
@TableName("park_merchant")
@KeySequence("park_merchant_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [商户名称]
     */
    private String merchantName;
    /**
     * [商户编码] 唯一商户编码
     */
    private String merchantCode;
    /**
     * [联系人]
     */
    private String contactPerson;
    /**
     * [联系电话]
     */
    private String contactPhone;
    /**
     * [商户地址]
     */
    private String address;
    /**
     * [经营范围]
     */
    private String businessScope;
    /**
     * [区域编码] 关联park_area.area_code
     */
    private String regionCode;
    /**
     * [统一社会信用代码]
     */
    private String creditCode;
    /**
     * [状态] 如:正常/禁用/待审核/已驳回
     */
    private String status;
    /**
     * [结算账户]
     */
    private String settlementAccount;
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

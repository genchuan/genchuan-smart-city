package cn.iocoder.yudao.module.park.dal.dataobject.park.user.address;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 地址 DO
 *
 * @author 亘川智城
 */
@TableName("park_address")
@KeySequence("park_address_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [用户ID] 关联park_user.id，可为NULL
     */
    private Long userId;
    /**
     * [企业ID] 关联park_enterprise_information.enterprise_id，可为NULL
     */
    private Long enterpriseId;
    /**
     * [收货人姓名]
     */
    private String receiverName;
    /**
     * [联系电话]
     */
    private String phone;
    /**
     * [省份]
     */
    private String province;
    /**
     * [城市]
     */
    private String city;
    /**
     * [区县]
     */
    private String district;
    /**
     * [详细地址]
     */
    private String detailAddress;
    /**
     * [是否默认地址] 如:0-否/1-是
     */
    private Boolean isDefault;
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

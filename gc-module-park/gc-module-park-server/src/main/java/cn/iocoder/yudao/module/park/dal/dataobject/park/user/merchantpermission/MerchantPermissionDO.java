package cn.iocoder.yudao.module.park.dal.dataobject.park.user.merchantpermission;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 商户权限 DO
 *
 * @author 亘川智城
 */
@TableName("park_merchant_permission")
@KeySequence("park_merchant_permission_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantPermissionDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [商户ID] 关联park_merchant.id
     */
    private Long merchantId;
    /**
     * [权限ID] 关联park_permission.perm_id
     */
    private Long permId;
    /**
     * [权限编码]
     */
    private String permCode;
    /**
     * [权限名称]
     */
    private String permName;
    /**
     * [生效时间]
     */
    private LocalDateTime effectTime;
    /**
     * [失效时间] 永久有效为NULL
     */
    private LocalDateTime expireTime;
    /**
     * [权限状态] 如:启用/禁用
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

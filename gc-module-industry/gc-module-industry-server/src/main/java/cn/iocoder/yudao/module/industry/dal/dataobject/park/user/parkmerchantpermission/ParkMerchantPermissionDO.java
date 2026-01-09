package cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmerchantpermission;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商户权限 DO
 *
 * @author lxs
 */
@TableName("park_merchant_permission")
@KeySequence("park_merchant_permission_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkMerchantPermissionDO extends BaseDO {

    /**
     * 主键ID[商户权限唯一标识]
     */
    @TableId
    private Long id;
    /**
     * 商户ID[关联商户ID，关联 park_merchant 表]
     */
    private Long merchantId;
    /**
     * 权限编码[关联 park_permission.perm_code]
     */
    private String permCode;
    /**
     * 权限状态[启用/禁用]
     */
    private String status;
    /**
     * 通用扩展字段1[预留扩展字段]
     */
    private String extCommon1;
    /**
     * 通用扩展字段2[预留扩展字段]
     */
    private String extCommon2;
    /**
     * 通用扩展字段3[预留扩展字段]
     */
    private String extCommon3;
    /**
     * 通用扩展字段4[预留扩展字段]
     */
    private String extCommon4;
    /**
     * 备注[权限相关备注说明]
     */
    private String remark;

}

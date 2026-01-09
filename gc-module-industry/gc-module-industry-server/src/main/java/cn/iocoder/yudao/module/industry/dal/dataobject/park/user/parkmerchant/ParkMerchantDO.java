package cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmerchant;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商户 DO
 *
 * @author lxs
 */
@TableName("park_merchant")
@KeySequence("park_merchant_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkMerchantDO extends BaseDO {

    /**
     * 主键ID[商户唯一标识]
     */
    @TableId
    private Long id;
    /**
     * 商户名称[唯一商户名称，如组织名称、公司名称等]
     */
    private String merchantName;
    /**
     * 商户编码[唯一商户编码]
     */
    private String merchantCode;
    /**
     * 联系人姓名[商户对外对内的联系人姓名，可重名]
     */
    private String contactPerson;
    /**
     * 联系电话[商户联系电话]
     */
    private String contactPhone;
    /**
     * 商户地址[商户经营或办公地址]
     */
    private String address;
    /**
     * 经营范围[商户经营范围，如停车场运营/车辆进出管理]
     */
    private String businessScope;
    /**
     * 状态[正常/停业/注销]
     */
    private String status;
    /**
     * 分账比例[默认分账比例，百分比数值]
     */
    private BigDecimal settlementRatio;
    /**
     * 创建人[数据创建人]
     */
    private String createBy;
    /**
     * 更新人[数据最后更新人]
     */
    private String updateBy;
    /**
     * 备注[商户相关备注说明]
     */
    private String remark;
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

}

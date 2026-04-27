package cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantlink;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商户对接 DO
 *
 * @author 亘川智城
 */
@TableName("merchant_link")
@KeySequence("merchant_link_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantLinkDO extends BaseDO {

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
     * 对接类型：数据对接/接口对接/商品同步/核销同步
     */
    private String linkType;
    /**
     * 接口地址
     */
    private String apiUrl;
    /**
     * 接口密钥
     */
    private String apiKey;
    /**
     * 对接状态：未对接/已对接
     */
    private String status;
    /**
     * 对接生效时间
     */
    private LocalDateTime effectTime;
    /**
     * 最后同步时间
     */
    private LocalDateTime lastSyncTime;
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
    /**
     * 商户名称
     */
    @TableField(exist = false)
    private String merchantName;

}
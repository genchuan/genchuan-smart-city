package cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.asset;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

import java.math.BigInteger;

/**
 * 资产 DO
 *
 * @author 芋道源码
 */
@TableName("asset")
@KeySequence("asset_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetDO extends BaseDO {

    /**
     * 资产ID
     */
    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * TB租户ID
     */
    private String tenantId;

    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 资产名称
     */
    private String name;

    /**
     * 资产类型
     */
    private String type;

    /**
     * 标签
     */
    private String label;

    /**
     * 资产实体ID
     */
    private String assetProfileId;

    /**
     * 附加信息
     */
    private String additionalInfo;

    /**
     * 外部ID
     */
    private String externalId;

    /**
     * 版本
     */
    private Long version;

    /**
     * 创建时间
     */
    private BigInteger createdTime;

}
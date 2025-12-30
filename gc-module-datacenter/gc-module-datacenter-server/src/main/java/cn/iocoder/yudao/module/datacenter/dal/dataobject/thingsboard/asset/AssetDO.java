package cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.asset;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

import java.math.BigInteger;

/**
 * 资产信息 DO
 *
 * @author zhucongquan
 */
@TableName("tb_asset_info")
@KeySequence("tb_asset_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 资产ID
     */
    private String assetId;
    /**
     * 实体类型
     */
    private String entityType;
    /**
     * 创建时间戳
     */
    private Long createdTime;
    /**
     * 租户实体类型
     */
    private String tenantEntityType;
    /**
     * 客户ID
     */
    private String customerId;
    /**
     * 客户实体类型
     */
    private String customerEntityType;
    /**
     * 资产档案ID
     */
    private String assetProfileId;
    /**
     * 资产档案实体类型
     */
    private String assetProfileEntityType;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 资产类型
     */
    private String assetType;
    /**
     * 资产标签
     */
    private String assetLabel;
    /**
     * 外部ID
     */
    private String externalId;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 客户标题
     */
    private String customerTitle;
    /**
     * 客户是否公开
     */
    private Boolean customerIsPublic;
    /**
     * 资产档案名称
     */
    private String assetProfileName;
    /**
     * 附加信息
     */
    private String additionalInfo;
    /**
     * 属性列表
     */
    private String attributes;
    /**
     * 关联设备列表
     */
    private String contextDevices;
    /**
     * 系统租户ID
     */
    private Long tenantIdSys;

    @TableField(exist = false)
    private Object attributesJson;

    @TableField(exist = false)
    private Object contextDevicesJson;

    /**
     * 关联资产列表（向外关联）
     */
    private String contextAsset;

    @TableField(exist = false)
    private Object contextAssetJson;

    /**
     * 通用扩展字段1
     */
    private String extCommon1;

    /**
     * 通用扩展字段2
     */
    private String extCommon2;

    /**
     * 通用扩展字段3
     */
    private String extCommon3;

    /**
     * 通用扩展字段4
     */
    private String extCommon4;
}
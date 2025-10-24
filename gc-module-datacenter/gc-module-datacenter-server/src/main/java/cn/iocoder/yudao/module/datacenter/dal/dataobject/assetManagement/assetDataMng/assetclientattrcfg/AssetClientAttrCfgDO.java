package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetclientattrcfg;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产客户端属性配置 DO
 *
 * @author 亘川智城
 */
@TableName("biz_asset_client_attr_cfg")
@KeySequence("biz_asset_client_attr_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetClientAttrCfgDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 客户端属性ID
     */
    private String assetClientAttrId;
    /**
     * 关联资产ID
     */
    private String relAssetId;
    /**
     * 关联资产名称
     */
    private String relAssetName;
    /**
     * 属性名称
     */
    private String attrName;
    /**
     * 属性代码
     */
    private String attrCode;
    /**
     * 属性数据类型
     */
    private String attrDataType;
    /**
     * 属性值
     */
    private String attrValue;
    /**
     * 属性用途
     */
    private String attrPurpose;
    /**
     * 客户端IP
     */
    private String clientIp;
    /**
     * 配置时间
     */
    private LocalDateTime cfgTime;
    /**
     * 操作人
     */
    private String operUser;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}
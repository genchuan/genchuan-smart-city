package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetshareattrcfg;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产共享属性配置 DO
 *
 * @author 亘川智城
 */
@TableName("biz_asset_share_attr_cfg")
@KeySequence("biz_asset_share_attr_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetShareAttrCfgDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 共享属性ID
     */
    private String assetShareAttrId;
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
     * 属性值
     */
    private String attrValue;
    /**
     * 共享对象类型
     */
    private String shareObjType;
    /**
     * 共享对象ID
     */
    private String shareObjId;
    /**
     * 共享对象名称
     */
    private String shareObjName;
    /**
     * 共享权限
     */
    private String sharePerm;
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
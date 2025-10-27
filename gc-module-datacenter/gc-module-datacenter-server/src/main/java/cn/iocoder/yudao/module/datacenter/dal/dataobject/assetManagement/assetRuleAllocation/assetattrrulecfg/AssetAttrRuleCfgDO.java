package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetattrrulecfg;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产属性规则配置 DO
 *
 * @author 亘川智城
 */
@TableName("biz_asset_attr_rule_cfg")
@KeySequence("biz_asset_attr_rule_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetAttrRuleCfgDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 属性规则ID
     */
    private String assetAttrRuleId;
    /**
     * 关联资产分类ID
     */
    private String relAssetCatId;
    /**
     * 关联资产分类名称
     */
    private String relAssetCatName;
    /**
     * 属性名称
     */
    private String assetAttrName;
    /**
     * 属性代码
     */
    private String assetAttrCode;
    /**
     * 属性数据类型
     */
    private String attrDataType;
    /**
     * 属性长度
     */
    private String attrLength;
    /**
     * 属性值域
     */
    private String attrValueRange;
    /**
     * 是否必选
     */
    private String isRequired;
    /**
     * 属性说明
     */
    private String attrDesc;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
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
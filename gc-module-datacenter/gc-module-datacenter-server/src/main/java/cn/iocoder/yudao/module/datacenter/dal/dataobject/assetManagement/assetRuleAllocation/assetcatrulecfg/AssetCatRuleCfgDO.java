package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetcatrulecfg;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产分类规则配置 DO
 *
 * @author 亘川智城
 */
@TableName("biz_asset_cat_rule_cfg")
@KeySequence("biz_asset_cat_rule_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetCatRuleCfgDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分类规则ID
     */
    private String assetCatRuleId;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 大类编码位数
     */
    private Integer majorCodeLength;
    /**
     * 中类编码位数
     */
    private Integer midCodeLength;
    /**
     * 小类编码位数
     */
    private Integer minorCodeLength;
    /**
     * 编码生成逻辑
     */
    private String codeGenLogic;
    /**
     * 适用资产领域
     */
    private String assetDom;
    /**
     * 启用状态
     */
    private String enableStatus;
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
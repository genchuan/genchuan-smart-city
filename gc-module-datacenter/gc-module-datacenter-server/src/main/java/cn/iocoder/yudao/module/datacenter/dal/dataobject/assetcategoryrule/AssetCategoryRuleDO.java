package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetcategoryrule;

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
@TableName("gc_asset_category_rule")
@KeySequence("gc_asset_category_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetCategoryRuleDO extends BaseDO {

    public static final Long PARENT_ID_ROOT = 0L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 父级编号
     */
    private Long parentId;
    /**
     * 名字
     */
    private String name;
    /**
     * 分类规则ID
     */
    private String categoryRuleId;
    /**
     * 父类规则ID
     */
    private String parentCategoryRuleId;
    /**
     * 分类层级
     */
    private String categoryLevel;
    /**
     * 分类代码
     */
    private String categoryCode;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类说明
     */
    private String categoryDesc;
    /**
     * 启用状态
     */
    private String enableStatus;
    /**
     * 创建人
     */
    private String createdUser;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 更新人
     */
    private String updatedUser;
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
    /**
     * 扩展字段1
     */
    private String extCategory1;
    /**
     * 扩展字段2
     */
    private String extCategory2;

}
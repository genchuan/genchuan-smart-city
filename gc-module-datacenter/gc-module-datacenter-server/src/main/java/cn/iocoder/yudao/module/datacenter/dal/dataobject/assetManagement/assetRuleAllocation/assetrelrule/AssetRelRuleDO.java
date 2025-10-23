package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetrelrule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产关联规则配置 DO
 *
 * @author 亘川智城
 */
@TableName("gc_asset_rel_rule")
@KeySequence("gc_asset_rel_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetRelRuleDO extends BaseDO {

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
     * 关联规则ID（UUID）
     */
    private String relRuleId;
    /**
     * 资产分类ID（小类）
     */
    private String assetCategoryId;
    /**
     * 资产分类名称
     */
    private String assetCategoryName;
    /**
     * 关联对象类型
     */
    private String relObjType;
    /**
     * 关联对象ID
     */
    private String relObjId;
    /**
     * 关联对象名称
     */
    private String relObjName;
    /**
     * 关联必填标识：1必选/0可选
     */
    private String isRequired;
    /**
     * 关联校验规则
     */
    private String relCheckRule;
    /**
     * 启用状态：1启用/0禁用
     */
    private String enableStatus;
    /**
     * 创建人（用户ID）
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 更新人（用户ID）
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
    /**
     * 分类扩展字段1
     */
    private String extCategory1;
    /**
     * 分类扩展字段2
     */
    private String extCategory2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}
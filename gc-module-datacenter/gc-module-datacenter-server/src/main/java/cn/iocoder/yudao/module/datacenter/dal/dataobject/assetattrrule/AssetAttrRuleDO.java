package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetattrrule;

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
@TableName("gc_asset_attr_rule")
@KeySequence("gc_asset_attr_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetAttrRuleDO extends BaseDO {

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
     * 属性规则ID
     */
    private String attrRuleId;
    /**
     * 资产分类ID
     */
    private String assetCategoryId;
    /**
     * 资产分类名称
     */
    private String assetCategoryName;
    /**
     * 属性名称
     */
    private String attrName;
    /**
     * 属性代码
     */
    private String attrCode;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字段长度
     */
    private Integer fieldLength;
    /**
     * 是否必选
     */
    private String isRequired;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 值域范围
     */
    private String valueRange;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 属性说明
     */
    private String attrDesc;
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
    /**
     * 名字
     */
    private String name;

}
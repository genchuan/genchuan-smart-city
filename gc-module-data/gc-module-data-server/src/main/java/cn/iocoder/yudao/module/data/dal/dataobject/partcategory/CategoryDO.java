package cn.iocoder.yudao.module.data.dal.dataobject.partcategory;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件分类 DO
 *
 * @author zhucongquan
 */
@TableName("part_category")
@KeySequence("part_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类代码
     */
    private String categoryCode;
    /**
     * 编码排序类型
     */
    private String codeSortType;
    /**
     * 上级分类ID
     */
    private String parentId;
    /**
     * 上级分类名称
     */
    private String parentCategoryName;
    /**
     * 图示名称
     */
    private String iconName;
    /**
     * 图示审核状态
     */
    private String auditStatus;
    /**
     * 分类类型
     */
    private String categoryType;
    /**
     * 状态
     */
    private String status;
    /**
     * 关联审核状态ID
     */
    private String auditStatusId;
    /**
     * 关联实例数
     */
    private Integer instanceCount;
    /**
     * 用途说明
     */
    private String purpose;
    /**
     * 分类变更通知标识
     */
    private Boolean notifyFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

    /**
     * 创建人
     */
    private String creator;

}
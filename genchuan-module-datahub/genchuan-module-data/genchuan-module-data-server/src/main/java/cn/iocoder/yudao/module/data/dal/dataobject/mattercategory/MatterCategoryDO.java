package cn.iocoder.yudao.module.data.dal.dataobject.mattercategory;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 管理事项分类 DO
 *
 * @author zhucongquan
 */
@TableName("matter_category")
@KeySequence("matter_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatterCategoryDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 管理事项分类ID
     */
    private String matterCategoryId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类代码
     */
    private String categoryCode;
    /**
     * 上级分类ID
     */
    private String parentId;
    /**
     * 上级分类名称
     */
    private String parentName;
    /**
     * 主管部门ID
     */
    private String deptId;
    /**
     * 主管部门名称
     */
    private String deptName;
    /**
     * 处置时限（小时）
     */
    private Integer dealLimit;
    /**
     * 工作流ID
     */
    private String workflowId;
    /**
     * 工作流编码
     */
    private String workflowCode;
    /**
     * 工作流描述
     */
    private String workflowDesc;
    /**
     * 分类类型ID
     */
    private String categoryTypeId;
    /**
     * 分类类型名称
     */
    private String categoryType;
    /**
     * 状态
     */
    private String status;
    /**
     * 审核状态
     */
    private String auditStatus;
    /**
     * 关联事项数
     */
    private Integer relatedMatterCount;
    /**
     * 用途说明
     */
    private String purpose;
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
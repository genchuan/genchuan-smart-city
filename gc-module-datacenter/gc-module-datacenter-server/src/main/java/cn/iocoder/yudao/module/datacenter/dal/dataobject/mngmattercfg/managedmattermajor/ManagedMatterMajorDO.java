package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.managedmattermajor;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
        import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理事项分类表（含大类和小类） DO
 *
 * @author 亘川智城
 */
@TableName("gc_managed_matter_major")
@KeySequence("gc_managed_matter_major_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagedMatterMajorDO extends BaseDO {

    /**
     * 事项ID
     */
    @TableId
    private Long id;
    /**
     * 父类ID（关联本表id，大类为NULL）
     */
    private String parentId;
    /**
     * 事项代码（大类2位/小类3位）
     */
    private String matterCode;
    /**
     * 事项名称
     */
    private String matterName;
    /**
     * 事项说明
     */
    private String matterDesc;
    /**
     * 排序序号
     */
    private Integer sortNum;
    /**
     * 启用状态（1启用/0禁用）
     */
    private String enableStatus;
    /**
     * 是否扩展类（仅小类使用：0标准类/1扩展类）
     */
    private String isExtend;

    /**
     * 主管部门代码（仅小类使用）
     */
    private String deptCode;
    /**
     * 主管部门名称（仅小类使用）
     */
    private String deptName;
    /**
     * 工作流模型标识
     */
    private String flowInstanceId;
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

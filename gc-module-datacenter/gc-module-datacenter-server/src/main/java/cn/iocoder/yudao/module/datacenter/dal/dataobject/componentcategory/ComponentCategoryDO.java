package cn.iocoder.yudao.module.datacenter.dal.dataobject.componentcategory;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测部件分类 DO
 *
 * @author lll
 */
@TableName("gc_component_category")
@KeySequence("gc_component_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComponentCategoryDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分类ID，UUID
     */
    private String categoryId;
    /**
     * 父类ID，0表示大类
     */
    private String parentId;
    /**
     * 层级：1-大类/2-中类/3-小类
     */
    private String level;
    /**
     * 分类代码：01-99
     */
    private String code;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类说明
     */
    private String description;
    /**
     * 状态：1-启用/0-禁用
     */
    private String status;
    /**
     * 创建人ID
     */
    private String createUser;
    /**
     * 更新人ID
     */
    private String updateUser;
    /**
     * 扩展字段1
     */
    private String ext1;
    /**
     * 扩展字段2
     */
    private String ext2;
    /**
     * 扩展字段3
     */
    private String ext3;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}
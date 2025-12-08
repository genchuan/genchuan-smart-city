package cn.iocoder.yudao.module.datacenter.dal.dataobject.appscenecategory;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 应用场景分类配置 DO
 *
 * @author zcq
 */
@TableName("sys_app_scene_category")
@KeySequence("sys_app_scene_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppSceneCategoryDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 应用场景分类代码
     */
    private String sceneCatCode;
    /**
     * 应用场景分类名称
     */
    private String sceneCatName;
    /**
     * 分类层级：1-大类/2-中类/3-小类
     */
    private String catLevel;
    /**
     * 父级分类ID
     */
    private String parentCatId;
    /**
     * 父级分类名称
     */
    private String parentCatName;
    /**
     * 应用场景分类说明
     */
    private String sceneCatDesc;
    /**
     * 是否扩展类：0-标准类/1-扩展类
     */
    private String isExt;
    /**
     * 启用状态：1-启用/0-禁用
     */
    private String enableStatus;
    /**
     * 关联监测部件类型ID
     */
    private String relCompCatId;
    /**
     * 关联监测部件类型名称
     */
    private String relCompCatName;
    /**
     * 关联监测事件类型ID
     */
    private String relEvtCatId;
    /**
     * 关联监测事件类型名称
     */
    private String relEvtCatName;
    /**
     * 创建人(业务)
     */
    private String createUserBiz;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
    /**
     * 更新人(业务)
     */
    private String updateUserBiz;
    /**
     * 更新时间(业务)
     */
    private LocalDateTime updateTimeBiz;
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

    /**
     * 子节点列表（用于树形结构）
     */
    @TableField(exist = false) // 表示该字段不是数据库表中的列
    private List<AppSceneCategoryDO> children;

    /**
     * 关联的菜单编号
     */
    private String menuIds;
}
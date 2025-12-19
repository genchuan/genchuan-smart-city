package cn.iocoder.yudao.module.datacenter.dal.dataobject.homequickmenu;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 系统首页快捷菜单配置 DO
 *
 * @author zhucongquan
 */
@TableName("sys_home_quick_menu")
@KeySequence("sys_home_quick_menu_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeQuickMenuDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 菜单类型
     */
    private String type;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 排序序号
     */
    private Integer sortNum;
    /**
     * 启用状态
     */
    private String enableStatus;
    /**
     * 菜单描述
     */
    private String menuDesc;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}
package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedcomponentmajorconfig;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件大类配置表 DO
 *
 * @author 亘川智城
 */
@TableName("gc_managed_component_major_config")
@KeySequence("gc_managed_component_major_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagedComponentMajorConfigDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 大类ID，唯一编码，采用UUID
     */
    private String majorId;
    /**
     * 大类代码，01-99
     */
    private String majorCode;
    /**
     * 大类名称，如市政公用设施/交通设施等
     */
    private String majorName;
    /**
     * 大类说明，描述包含的管理部件范围
     */
    private String majorDesc;
    /**
     * 排序序号，1-999
     */
    private Integer sortNum;
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
    private String extField1;
    /**
     * 扩展字段2
     */
    private String extField2;
    /**
     * 扩展字段3
     */
    private String extField3;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}
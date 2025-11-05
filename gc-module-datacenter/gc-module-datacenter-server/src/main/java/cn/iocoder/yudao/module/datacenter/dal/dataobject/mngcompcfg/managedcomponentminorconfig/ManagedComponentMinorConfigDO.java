package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedcomponentminorconfig;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件小类配置 DO
 *
 * @author 亘川智城
 */
@TableName("gc_managed_component_minor_config")
@KeySequence("gc_managed_component_minor_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagedComponentMinorConfigDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 小类ID，唯一编码，采用UUID
     */
    private String minorId;
    /**
     * 关联的大类ID
     */
    private String majorId;
    /**
     * 小类代码，001-999
     */
    private String minorCode;
    /**
     * 小类名称，如上水井盖/污水井盖等
     */
    private String minorName;
    /**
     * 小类说明，描述具体定义及特征
     */
    private String minorDesc;
    /**
     * 主管部门统一社会信用代码
     */
    private String deptCode;
    /**
     * 主管部门全称
     */
    private String deptName;
    /**
     * 是否扩展类：0-标准类/1-扩展类
     */
    private String isExtend;
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
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}
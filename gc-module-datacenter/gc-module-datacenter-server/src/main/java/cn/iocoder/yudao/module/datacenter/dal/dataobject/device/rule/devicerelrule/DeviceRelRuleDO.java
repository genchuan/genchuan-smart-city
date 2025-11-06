package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.devicerelrule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备关联规则配置 DO
 *
 * @author zhucongquan
 */
@TableName("sys_device_rel_rule")
@KeySequence("sys_device_rel_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRelRuleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联规则ID
     */
    private String relRuleId;
    /**
     * 设备分类ID
     */
    private String deviceCatId;
    /**
     * 设备分类名称
     */
    private String deviceCatName;
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
     * 关联必选标识
     */
    private String isRequired;
    /**
     * 关联校验规则
     */
    private String relCkRule;
    /**
     * 启用状态
     */
    private String enableStatus;
    /**
     * 创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 更新时间
     */
    private LocalDateTime updateTimeSys;
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

}
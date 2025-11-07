package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.devicecatrule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备分类规则配置 DO
 *
 * @author zhucongquan
 */
@TableName("sys_device_cat_rule")
@KeySequence("sys_device_cat_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCatRuleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分类规则ID
     */
    private String catRuleId;
    /**
     * 父类规则ID
     */
    private String parentCatRuleId;
    /**
     * 分类层级
     */
    private String catLevel;
    /**
     * 分类代码
     */
    private String catCode;
    /**
     * 分类名称
     */
    private String catName;
    /**
     * 分类说明
     */
    private String catDesc;
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
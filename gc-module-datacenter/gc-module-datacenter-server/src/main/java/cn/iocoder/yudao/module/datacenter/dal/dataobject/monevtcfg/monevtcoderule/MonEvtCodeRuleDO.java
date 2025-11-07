package cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtcoderule;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测事件标识码规则 DO
 *
 * @author 亘川智城
 */
@TableName("sys_mon_evt_code_rule")
@KeySequence("sys_mon_evt_code_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonEvtCodeRuleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规则ID，唯一编码，UUID生成
     */
    private String monEvtRuleId;
    /**
     * 规则名称，如“监测事件18位标识码规则”
     */
    private String ruleName;
    /**
     * 行政代码位数，固定为6位，符合GB/T 2260
     */
    private Integer adminCodeLen;
    /**
     * 大类代码位数，固定为2位，关联事件分类表大类代码；监测事件分类配置表（sys_mon_evt_cat）
     */
    private Integer majorCodeLen;
    /**
     * 中类代码位数，固定为2位，关联事件分类表中类代码；监测事件分类配置表（sys_mon_evt_cat）
     */
    private Integer midCodeLen;
    /**
     * 小类代码位数，固定为2位，关联事件分类表小类代码；监测事件分类配置表（sys_mon_evt_cat）
     */
    private Integer minorCodeLen;
    /**
     * 顺序码位数，固定为6位，从000001顺序编排
     */
    private Integer seqCodeLen;
    /**
     * 顺序码生成规则，同一行政+小类下按上报时间递增，默认“同一县级行政区域+小类下按上报时间递增”
     */
    private String seqGenRule;
    /**
     * 启用状态，同一时间仅一个规则启用，1（启用）/0（禁用）
     */
    private String enableStatus;
    /**
     * 创建人，填写创建人账号，用户信息表(sys_user)
     */
    private String createUser;
    /**
     * 更新人，填写更新人账号，用户信息表(sys_user)
     */
    private String updateUser;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}
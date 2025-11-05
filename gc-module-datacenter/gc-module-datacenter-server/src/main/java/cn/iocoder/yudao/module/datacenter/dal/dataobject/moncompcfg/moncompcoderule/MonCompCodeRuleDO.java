package cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompcoderule;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测部件标识码规则 DO
 *
 * @author 亘川智城
 */
@TableName("sys_mon_comp_code_rule")
@KeySequence("sys_mon_comp_code_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonCompCodeRuleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规则ID，唯一编码，UUID生成
     */
    private String codeRuleId;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 规则说明
     */
    private String ruleDesc;
    /**
     * 编码格式
     */
    private String codeFormat;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}
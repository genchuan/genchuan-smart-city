package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalrulecat;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 规则分类管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_rule_cat")
@KeySequence("sys_eval_rule_cat_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalRuleCatDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规则分类ID
     */
    private String ruleCatId;
    /**
     * 规则分类名称
     */
    private String ruleCatName;
    /**
     * 分类编码
     */
    private String ruleCatCode;
    /**
     * 适用指标体系ID
     */
    private String idxSystemId;
    /**
     * 适用指标体系名称
     */
    private String idxSystemName;
    /**
     * 分类描述
     */
    private String catDesc;
    /**
     * 启用状态
     */
    private String enableStatus;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新人
     */
    private String updateUser;
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
     * 创建时间(系统)
     */
    private LocalDateTime createTimeSys;
    /**
     * 更新时间(系统)
     */
    private LocalDateTime updateTimeSys;

}
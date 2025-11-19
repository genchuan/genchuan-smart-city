package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrptarch;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 报告归档导出 DO
 *
 * @author zcq
 */
@TableName("sys_eval_rpt_arch")
@KeySequence("sys_eval_rpt_arch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalRptArchDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 归档ID
     */
    private String rptArchId;
    /**
     * 归档编号
     */
    private String rptArchCode;
    /**
     * 关联报告ID
     */
    private String evalRptId;
    /**
     * 报告名称
     */
    private String evalRptName;
    /**
     * 评价对象ID
     */
    private String evalObjectId;
    /**
     * 评价对象名称
     */
    private String evalObjectName;
    /**
     * 归档位置
     */
    private String archLocation;
    /**
     * 归档时间
     */
    private LocalDateTime archTime;
    /**
     * 归档方式
     */
    private String archMethod;
    /**
     * 报告版本
     */
    private String rptVersion;
    /**
     * 归档状态
     */
    private String archStatus;
    /**
     * 失败原因
     */
    private String failReason;
    /**
     * 创建人(业务)
     */
    private String createUserBiz;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
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
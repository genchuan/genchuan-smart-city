package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrpt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 评价报告 DO
 *
 * @author zcq
 */
@TableName("sys_eval_rpt")
@KeySequence("sys_eval_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 报告ID
     */
    private String evalRptId;
    /**
     * 报告名称
     */
    private String evalRptName;
    /**
     * 报告编码
     */
    private String evalRptCode;
    /**
     * 关联模板ID
     */
    private String rptTemplateId;
    /**
     * 关联模板名称
     */
    private String rptTemplateName;
    /**
     * 关联存档ID
     */
    private String resultArchId;
    /**
     * 评价对象ID
     */
    private String evalObjectId;
    /**
     * 评价对象名称
     */
    private String evalObjectName;
    /**
     * 报告文件路径
     */
    private String rptFilePath;
    /**
     * 报告状态
     */
    private String rptStatus;
    /**
     * 生成时间
     */
    private LocalDateTime generateTime;
    /**
     * 补充内容
     */
    private String suppleContent;
    /**
     * 生成人
     */
    private String generateUser;
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
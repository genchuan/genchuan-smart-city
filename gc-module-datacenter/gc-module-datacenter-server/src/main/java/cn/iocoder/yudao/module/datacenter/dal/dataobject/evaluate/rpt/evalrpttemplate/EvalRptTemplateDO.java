package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrpttemplate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 报告模板配置 DO
 *
 * @author zcq
 */
@TableName("sys_eval_rpt_template")
@KeySequence("sys_eval_rpt_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalRptTemplateDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 模板ID
     */
    private String rptTemplateId;
    /**
     * 模板名称
     */
    private String rptTemplateName;
    /**
     * 模板编码
     */
    private String rptTemplateCode;
    /**
     * 适用任务类型
     */
    private String applyTaskType;
    /**
     * 模板文件路径
     */
    private String templateFilePath;
    /**
     * 报告结构
     */
    private String rptStructure;
    /**
     * 动态字段映射
     */
    private String dynamicFieldMap;
    /**
     * 固定内容
     */
    private String fixedContent;
    /**
     * 模板版本
     */
    private String templateVersion;
    /**
     * 启用状态
     */
    private String enableStatus;
    /**
     * 创建人(业务)
     */
    private String createUserBiz;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
    /**
     * 更新人(业务)
     */
    private String updateUserBiz;
    /**
     * 更新时间(业务)
     */
    private LocalDateTime updateTimeBiz;
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
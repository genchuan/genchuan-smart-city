package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultrpt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 结果报表管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_result_rpt")
@KeySequence("sys_eval_result_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalResultRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 报表ID
     */
    private String resultRptId;
    /**
     * 报表名称
     */
    private String resultRptName;
    /**
     * 报表编码
     */
    private String resultRptCode;
    /**
     * 关联任务ID
     */
    private String evalTaskId;
    /**
     * 关联任务名称
     */
    private String evalTaskName;
    /**
     * 报表类型
     */
    private String rptType;
    /**
     * 统计维度
     */
    private String statDimension;
    /**
     * 行政区划代码
     */
    private String regionCode;
    /**
     * 行政区划名称
     */
    private String regionName;
    /**
     * 报表字段
     */
    private String rptFields;
    /**
     * 生成时间
     */
    private LocalDateTime generateTime;
    /**
     * 报表状态
     */
    private String rptStatus;
    /**
     * 文件路径
     */
    private String filePath;
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
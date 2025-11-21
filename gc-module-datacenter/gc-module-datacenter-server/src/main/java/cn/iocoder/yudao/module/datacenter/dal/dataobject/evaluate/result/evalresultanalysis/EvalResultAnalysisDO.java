package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultanalysis;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 评价结果分析 DO
 *
 * @author zcq
 */
@TableName("sys_eval_result_analysis")
@KeySequence("sys_eval_result_analysis_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalResultAnalysisDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分析ID
     */
    private String resultAnalysisId;
    /**
     * 分析名称
     */
    private String resultAnalysisName;
    /**
     * 分析编码
     */
    private String resultAnalysisCode;
    /**
     * 关联任务ID
     */
    private String evalTaskId;
    /**
     * 关联任务名称
     */
    private String evalTaskName;
    /**
     * 分析维度
     */
    private String analysisDimension;
    /**
     * 行政区划代码
     */
    private String regionCode;
    /**
     * 行政区划名称
     */
    private String regionName;
    /**
     * 指标项ID
     */
    private String idxItemId;
    /**
     * 指标项名称
     */
    private String idxItemName;
    /**
     * 时间范围
     */
    private String timeRange;
    /**
     * 分析结果
     */
    private String analysisResult;
    /**
     * 图表类型
     */
    private String chartType;
    /**
     * 生成时间
     */
    private LocalDateTime generateTime;
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
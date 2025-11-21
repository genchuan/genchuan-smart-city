package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresulteval;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检结果评估 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_result_eval")
@KeySequence("biz_inspect_result_eval_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectResultEvalDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 评估ID
     */
    private String evalId;
    /**
     * 评估编码
     */
    private String evalCode;
    /**
     * 评估周期
     */
    private String evalCycle;
    /**
     * 评估周期名称
     */
    private String evalCycleName;
    /**
     * 所属区域代码
     */
    private String areaCode;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 日常巡查合格率
     */
    private BigDecimal dailyQualifiedRate;
    /**
     * 专项巡查整改率
     */
    private BigDecimal specRectifyRate;
    /**
     * 一级问题处置及时率
     */
    private BigDecimal level1TimelyRate;
    /**
     * 问题集中区域
     */
    private String problemConcentratedArea;
    /**
     * 主要问题类型
     */
    private String mainProblemType;
    /**
     * 评估意见
     */
    private String evalOpinion;
    /**
     * 评估人
     */
    private String evalUser;
    /**
     * 评估时间
     */
    private LocalDateTime evalTime;
    /**
     * 评估报告URL
     */
    private String evalRptUrl;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime bizCreateTime;
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
package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspectionstatistics;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 环卫考核统计结果 DO
 *
 * @author zhucongquan
 */
@TableName("sys_inspection_statistics")
@KeySequence("sys_inspection_statistics_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionStatisticsDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 考核日期
     */
    private String inspectionDate;
    /**
     * 场所类型
     */
    private String areaType;
    /**
     * 区域/地点名称
     */
    private String areaName;
    /**
     * 该场所总得分
     */
    private BigDecimal totalScore;
    /**
     * 该类型满分
     */
    private BigDecimal maxScore;
    /**
     * 权重
     */
    private BigDecimal weight;
    /**
     * 最终得分
     */
    private BigDecimal scoreWeighted;
    /**
     * 样本数
     */
    private Integer sampleCount;
    /**
     * 考核状态
     */
    private String inspectionStatus;

}
package cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionresultsa;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查结果 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_inspection_results_a")
@KeySequence("smartcity_inspection_results_a_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionResultsADO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 编号
     */
    private String number;
    /**
     * 巡查人员
     */
    private String patrolPersonnel;
    /**
     * 巡查时间
     */
    private LocalDateTime patrolTime;
    /**
     * 巡查地点
     */
    private String patrolLocation;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源编号
     */
    private String resourceNumber;
    /**
     * 检查项目
     */
    private String inspectionItems;
    /**
     * 检查结果
     */
    private String inspectionResults;

}
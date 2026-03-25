package cn.iocoder.yudao.module.smartcity.dal.dataobject.developmentinspectionplan;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查计划管理 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_development_inspection_plan")
@KeySequence("smartcity_development_inspection_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevelopmentInspectionPlanDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 巡查时间
     */
    private String patrolTime;
    /**
     * 区域
     */
    private String region;
    /**
     * 人员
     */
    private String staff;

}
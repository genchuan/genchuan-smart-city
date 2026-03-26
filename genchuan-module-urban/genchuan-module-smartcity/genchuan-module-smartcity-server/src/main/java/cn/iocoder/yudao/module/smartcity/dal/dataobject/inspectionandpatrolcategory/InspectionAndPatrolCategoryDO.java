package cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionandpatrolcategory;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检类 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_inspection_and_patrol_category")
@KeySequence("smartcity_inspection_and_patrol_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionAndPatrolCategoryDO extends BaseDO {

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
     * 巡查巡检时间
     */
    private LocalDateTime inspectionAndPatrolTime;
    /**
     * 巡查巡检人员
     */
    private String patrolPersonnel;
    /**
     * 巡查巡检区域
     */
    private String patrolInspectionArea;
    /**
     * 巡查巡检内容
     */
    private String inspectionAndPatrolContent;
    /**
     * 异常情况描述
     */
    private String abnormalSituationDescription;
    /**
     * 备注
     */
    private String notes;

}
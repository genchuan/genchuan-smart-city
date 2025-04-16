package cn.iocoder.yudao.module.smartcity.dal.dataobject.alarmhandlingcategory;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 报警处置类 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_alarm_handling_category")
@KeySequence("smartcity_alarm_handling_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmHandlingCategoryDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 报警时间
     */
    private LocalDateTime time;
    /**
     * 报警来源
     */
    private String alarmSource;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 报警描述
     */
    private String alarmDescription;
    /**
     * 涉及区域
     */
    private String involvingRegions;
    /**
     * 处置措施
     */
    private String disposalMeasures;
    /**
     * 处置结果
     */
    private String disposalResults;
    /**
     * 备注
     */
    private String notes;

}
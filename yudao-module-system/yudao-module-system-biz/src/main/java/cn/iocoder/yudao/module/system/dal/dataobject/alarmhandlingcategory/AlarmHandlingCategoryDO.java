package cn.iocoder.yudao.module.system.dal.dataobject.alarmhandlingcategory;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 警报处理类别 DO
 *
 * @author zcq
 */
@TableName("system_alarm_handling_category")
@KeySequence("system_alarm_handling_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
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
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 所属部门
     */
    private String sysOrgCode;
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
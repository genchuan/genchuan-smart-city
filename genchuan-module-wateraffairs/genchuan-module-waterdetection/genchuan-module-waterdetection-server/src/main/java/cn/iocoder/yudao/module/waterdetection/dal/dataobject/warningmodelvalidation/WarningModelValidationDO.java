package cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningmodelvalidation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警模型校验 DO
 *
 * @author zcq
 */
@TableName("gc_warning_model_validation")
@KeySequence("gc_warning_model_validation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarningModelValidationDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 模型名称
     */
    private String modelName;
    /**
     * 校验时间段
     */
    private String validationPeriod;
    /**
     * 预警次数
     */
    private Double warningCount;
    /**
     * 准确预警次数
     */
    private Double accurateWarningCount;
    /**
     * 误报次数
     */
    private Double falseAlarmCount;
    /**
     * 准确率(%)
     */
    private Double accuracyRate;
    /**
     * 调整建议
     */
    private String adjustmentSuggestion;

}
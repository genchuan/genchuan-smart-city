package cn.iocoder.yudao.module.waterdetection.dal.dataobject.testresult;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 检测结果录入 DO
 *
 * @author zcq
 */
@TableName("gc_test_result")
@KeySequence("gc_test_result_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResultDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 样本编号
     */
    private String sampleCode;
    /**
     * 检测指标
     */
    private String testIndicator;
    /**
     * 检测值
     */
    private Double testValue;
    /**
     * 单位
     */
    private String unit;
    /**
     * 检测方法
     */
    private String testMethod;
    /**
     * 检测人员
     */
    private String testOperator;
    /**
     * 检测时间
     */
    private LocalDateTime testTime;
    /**
     * 设备编号
     */
    private String equipmentCode;

}
package cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingcapability;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 检测能力及设备管理 DO
 *
 * @author zcq
 */
@TableName("gc_testing_capability")
@KeySequence("gc_testing_capability_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestingCapabilityDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 机构编号
     */
    private String agencyCode;
    /**
     * 可检测指标
     */
    private String testableIndicators;
    /**
     * 设备型号
     */
    private String equipmentModel;
    /**
     * 设备编号
     */
    private String equipmentNo;
    /**
     * 校准记录
     */
    private String calibrationRecord;
    /**
     * 设备状态(正常/维修中/停用)
     */
    private String equipmentStatus;

}
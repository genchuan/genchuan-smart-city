package cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingassignment;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 采样人员分配 DO
 *
 * @author zcq
 */
@TableName("gc_sampling_assignment")
@KeySequence("gc_sampling_assignment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SamplingAssignmentDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 采样计划编号
     */
    private String planCode;
    /**
     * 采样点清单
     */
    private String pointList;
    /**
     * 负责人员
     */
    private String responsiblePerson;
    /**
     * 分配时间
     */
    private LocalDateTime assignTime;
    /**
     * 完成时限
     */
    private LocalDateTime deadline;
    /**
     * 联系方式
     */
    private String contactInfo;

}
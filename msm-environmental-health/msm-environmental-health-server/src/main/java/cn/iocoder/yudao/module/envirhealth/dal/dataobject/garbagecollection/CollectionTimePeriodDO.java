package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

import java.time.LocalDateTime;

/**
 * 收运时段字典 DO
 *
 * @author 亘川智城
 */
@TableName("sys_collection_time_period")
@KeySequence("sys_collection_time_period_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionTimePeriodDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 时段编码（如：uuid-time-period-001）
     */
    private String periodCode;
    /**
     * 时段名称（如：07:30-11:30）
     */
    private String periodName;
    /**
     * 时段开始时间
     */
    private LocalDateTime startTime;
    /**
     * 时段结束时间
     */
    private LocalDateTime endTime;
    /**
     * 排序号
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;

}
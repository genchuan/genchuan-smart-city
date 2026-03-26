package cn.iocoder.yudao.module.waterdetection.dal.dataobject.dmapartition;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * DMA分区划分与调整 DO
 *
 * @author zcq
 */
@TableName("gc_dma_partition")
@KeySequence("gc_dma_partition_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DmaPartitionDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 分区ID
     */
    private String partitionId;
    /**
     * 分区名称
     */
    private String partitionName;
    /**
     * 覆盖行政村
     */
    private String coveredVillages;
    /**
     * 边界坐标
     */
    private String boundaryCoordinates;
    /**
     * 包含监测点ID
     */
    private String monitorPointIds;
    /**
     * 划分日期
     */
    private LocalDateTime divisionDate;
    /**
     * 调整记录
     */
    private String adjustmentRecords;

}
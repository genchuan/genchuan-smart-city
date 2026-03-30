package cn.iocoder.yudao.module.waterdetection.dal.dataobject.invaliddata;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 不合格数据处理 DO
 *
 * @author zcq
 */
@TableName("gc_invalid_data")
@KeySequence("gc_invalid_data_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvalidDataDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 数据ID
     */
    private String dataId;
    /**
     * 仪器ID
     */
    private String instrumentId;
    /**
     * 监测值
     */
    private Double monitorValue;
    /**
     * 采集时间
     */
    private LocalDateTime collectionTime;
    /**
     * 数据状态(有效/无效)
     */
    private String dataStatus;
    /**
     * 无效原因
     */
    private String invalidReason;
    /**
     * 剔除标记(0未剔除1已剔除)
     */
    private Boolean isExcluded;
    /**
     * 处理人员ID
     */
    private String processorId;

}
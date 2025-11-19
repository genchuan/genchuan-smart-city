package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalrealtimedata;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 实时监测数据接入 DO
 *
 * @author zcq
 */
@TableName("sys_eval_real_time_data")
@KeySequence("sys_eval_real_time_data_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalRealTimeDataDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 数据ID
     */
    private String realTimeDataId;
    /**
     * 关联任务ID
     */
    private String evalTaskId;
    /**
     * 关联任务名称
     */
    private String evalTaskName;
    /**
     * 关联指标项ID
     */
    private String idxItemId;
    /**
     * 关联指标项名称
     */
    private String idxItemName;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 数据值
     */
    private String dataValue;
    /**
     * 数据时间
     */
    private LocalDateTime dataTime;
    /**
     * 接入状态
     */
    private String accessStatus;
    /**
     * 失败原因
     */
    private String failReason;
    /**
     * 同步频率
     */
    private Integer syncFrequency;
    /**
     * 创建人(业务)
     */
    private String createUserBiz;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}
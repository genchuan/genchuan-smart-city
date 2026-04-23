package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.leaverecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 离场记录 DO
 *
 * @author 亘川智城
 */
@TableName("leave_record")
@KeySequence("leave_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRecordDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 车牌
     */
    private String plateNo;
    /**
     * 入场时间
     */
    private LocalDateTime enterTime;
    /**
     * 离场时间
     */
    private LocalDateTime leaveTime;
    /**
     * 停车时长，单位：分钟，自动计算
     */
    private Integer parkDuration;
    /**
     * 记录状态：正常记录/异常记录，关联字典：leave_record_status
     */
    private String status;
    /**
     * 场站ID，关联场站表
     */
    private Long stationId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 佐证图片地址
     */
    private String proofImage;
    /**
     * 修正日志标记：0-未修正 1-已修正
     */
    private Boolean isCorrected;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
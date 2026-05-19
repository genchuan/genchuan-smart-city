package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.inparkstatus;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 在停状态 DO
 *
 * @author 亘川智城
 */
@TableName("in_park_status")
@KeySequence("in_park_status_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InParkStatusDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 场站ID，关联场站表station_info
     */
    private Long stationId;
    /**
     * 场站名称
     */
    private String stationName;
    /**
     * 车位ID，关联车位表parking_space_info
     */
    private Long spaceId;
    /**
     * 车牌号码
     */
    private String carNo;
    /**
     * 入场时间
     */
    private LocalDateTime inTime;
    /**
     * 是否超时长：是/否，关联字典in_park_status_over_time
     */
    private String overTime;
    /**
     * 状态：正常/异常，关联字典in_park_status_status
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
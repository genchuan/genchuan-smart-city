package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderalarm;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单告警 DO
 *
 * @author zhucongquan
 */
@TableName("order_alarm")
@KeySequence("order_alarm_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderAlarmDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 告警编号
     */
    private String alarmCode;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 车牌号
     */
    private String plateNo;
    /**
     * 异常类型
     */
    private String abnormalType;
    /**
     * 告警时间
     */
    private LocalDateTime alarmTime;
    /**
     * 关联充电桩编号
     */
    private String pileCode;
    /**
     * 告警状态
     */
    private String alarmStatus;
    /**
     * 核实结果
     */
    private String verifyResult;
    /**
     * 处理措施
     */
    private String handleMeasure;
    /**
     * 处理时间
     */
    private LocalDateTime handleTime;
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
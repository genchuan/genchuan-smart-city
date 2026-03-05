package cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carexit;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 离场记录 DO
 *
 * @author zhucongquan
 */
@TableName("park_car_exit")
@KeySequence("park_car_exit_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkCarExitDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 离场记录ID（UUID）
     */
    private String exitId;
    /**
     * 入场记录ID
     */
    private String entryId;
    /**
     * 车牌
     */
    private String carNumber;
    /**
     * 离场时间
     */
    private LocalDateTime exitTime;
    /**
     * 离场出入口ID
     */
    private String exitExitId;
    /**
     * 所属车场ID
     */
    private String lotId;
    /**
     * 停放时长（分钟）
     */
    private Integer parkingDuration;
    /**
     * 应缴费用
     */
    private BigDecimal feeAmount;
    /**
     * 实缴费用
     */
    private BigDecimal actualPayAmount;
    /**
     * 缴费状态：未缴费/已缴费/部分缴费
     */
    private String payStatus;
    /**
     * 缴费记录ID
     */
    private String paymentId;
    /**
     * 离场类型：正常/异常/特殊放行
     */
    private String exitType;
    /**
     * 异常原因
     */
    private String abnormalReason;
    /**
     * 识别设备
     */
    private String deviceCode;
    /**
     * 业务创建时间
     */
    private LocalDateTime exitCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime exitUpdateTime;
    /**
     * 业务备注
     */
    private String exitRemark;

}
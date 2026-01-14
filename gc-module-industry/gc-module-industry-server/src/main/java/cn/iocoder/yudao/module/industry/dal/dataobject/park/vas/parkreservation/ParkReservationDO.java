package cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkreservation;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车预约 DO
 *
 * @author lxs
 */
@TableName("park_reservation")
@KeySequence("park_reservation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkReservationDO extends BaseDO {

    /**
     * [主键ID] 停车预约记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [预约编号] 停车预约唯一编号
     */
    private String reservationNo;
    /**
     * [用户ID] 预约用户唯一标识
     */
    private Long userId;
    /**
     * [车牌] 预约车辆车牌号
     */
    private String carNumber;
    /**
     * [车场ID] 所属车场唯一标识
     */
    private Long lotId;
    /**
     * [车位ID] 所属车位唯一标识
     */
    private Long spaceId;
    /**
     * [预约日期] 用户预约的日期
     */
    private LocalDate reserveDate;
    /**
     * [预约开始时间] 预约开始时间
     */
    private LocalDateTime startTime;
    /**
     * [预约结束时间] 预约结束时间
     */
    private LocalDateTime endTime;
    /**
     * [预约状态] 如：待核验/已确认/已使用/已取消/已过期
     */
    private String status;
    /**
     * [核验时间] 预约核验时间
     */
    private LocalDateTime verifyTime;
    /**
     * [核验人] 核验人员唯一标识
     */
    private Long verifyBy;
    /**
     * [取消时间] 预约取消时间
     */
    private LocalDateTime cancelTime;
    /**
     * [取消原因] 预约取消原因
     */
    private String cancelReason;
    /**
     * [备注] 停车预约相关备注说明
     */
    private String remark;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;

}

package cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkarrears;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 欠费记录 DO
 *
 * @author lxs
 */
@TableName("park_arrears")
@KeySequence("park_arrears_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkArrearsDO extends BaseDO {

    /**
     * 主键ID，自增
     */
    @TableId
    private Long id;
    /**
     * 欠费单号，欠费唯一单号
     */
    private String arrearsNo;
    /**
     * 用户ID，关联 sys_user.id，可为 NULL
     */
    private Long userId;
    /**
     * 车牌，车辆车牌号
     */
    private String carNumber;
    /**
     * 关联逃费订单ID列表，以逗号分隔
     */
    private String orderIds;
    /**
     * 总欠费金额，该用户总欠费金额
     */
    private BigDecimal totalArrearsAmount;
    /**
     * 欠费状态：未追缴 / 追缴中 / 已结清 / 坏账
     */
    private String arrearsStatus;
    /**
     * 追缴方式，欠费追缴方式
     */
    private String traceWay;
    /**
     * 上次追缴时间
     */
    private LocalDateTime lastTraceTime;
    /**
     * 追缴记录，JSON 格式，不建议使用
     */
    private String traceRecord;
    /**
     * 备注，业务备注说明
     */
    private String remark;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}

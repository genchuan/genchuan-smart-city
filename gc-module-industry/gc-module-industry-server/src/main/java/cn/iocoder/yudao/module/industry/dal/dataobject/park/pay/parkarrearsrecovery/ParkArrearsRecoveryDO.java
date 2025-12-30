package cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkarrearsrecovery;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 欠费追缴 DO
 *
 * @author lxs
 */
@TableName("biz_park_arrears_recovery")
@KeySequence("biz_park_arrears_recovery_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkArrearsRecoveryDO extends BaseDO {

    /**
     * 主键ID，唯一标识
     */
    @TableId
    private Long id;
    /**
     * 欠费追缴唯一标识
     */
    private String recoveryCode;
    /**
     * 关联订单ID
     */
    private Long woId;
    /**
     * 订单编号
     */
    private String woNo;
    /**
     * 用户唯一标识
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户手机号
     */
    private String userPhone;
    /**
     * 欠费发生时间
     */
    private LocalDateTime arrearsTime;
    /**
     * 欠费结清时间
     */
    private LocalDateTime settleTime;
    /**
     * 欠费金额
     */
    private BigDecimal arrearsAmount;
    /**
     * 追缴工单唯一标识
     */
    private Long recoveryWoId;
    /**
     * 执行追缴的人员ID
     */
    private Long recoveryUserId;
    /**
     * 追缴处理状态
     */
    private String recoveryStatus;
    /**
     * 追缴方式
     */
    private String recoveryMethod;
    /**
     * 对用户采取的限制措施
     */
    private String limitMeasure;
    /**
     * 所属行政区划名称
     */
    private String regionName;
    /**
     * 所属网格名称
     */
    private String gridName;
    /**
     * 创建人
     */
    private String createUser;
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

package cn.iocoder.yudao.module.park.dal.dataobject.park.statrpt.chargeabnormal;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 收费异常 DO
 *
 * @author lxs
 */
@TableName("park_charge_abnormal")
@KeySequence("park_charge_abnormal_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeAbnormalDO extends BaseDO {

    /**
     * [主键ID] 自增主键
     */
    @TableId
    private Long id;
    /**
     * [异常订单编号]
     */
    private String orderNo;
    /**
     * [车牌号码]
     */
    private String carNumber;
    /**
     * [区域ID] 关联sys_area.id
     */
    private Long areaId;
    /**
     * [车场ID] 关联park_lot.id
     */
    private Long lotId;
    /**
     * [异常时间]
     */
    private LocalDateTime abnormalTime;
    /**
     * [异常金额]
     */
    private BigDecimal abnormalAmount;
    /**
     * [异常类型] 如:金额错误/计费缺失/重复收费/其他
     */
    private String abnormalType;
    /**
     * [异常原因]
     */
    private String abnormalReason;
    /**
     * [处置状态] 如:未处置/处理中/已处置
     */
    private String disposalStatus;
    /**
     * [处置结果] 如:已纠错/无法纠错
     */
    private String disposalResult;
    /**
     * [处置人ID] 关联park_user.id
     */
    private Long disposalBy;
    /**
     * [处置时间]
     */
    private LocalDateTime disposalTime;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}

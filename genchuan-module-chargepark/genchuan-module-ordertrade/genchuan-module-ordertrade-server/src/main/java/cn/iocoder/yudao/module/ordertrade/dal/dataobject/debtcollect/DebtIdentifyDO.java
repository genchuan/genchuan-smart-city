package cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 逃费识别 DO
 * @author genchuan
 */
@TableName("debt_identify")
@KeySequence("debt_identify_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DebtIdentifyDO extends BaseDO {

    /** 主键ID */
    @TableId
    private Long id;

    /** 识别编号，唯一 */
    private String identifyNo;

    /** 车牌 */
    private String plateNo;

    /** 欠费金额 */
    private BigDecimal arrearAmount;

    /** 识别时间 */
    private LocalDateTime identifyTime;

    /** 状态，字典：debt_identify_status */
    private String status;

    /** 所属场站ID */
    private Long stationId;

    /** 操作人ID */
    private Long operatorId;

    /** 备用字段1 */
    private String reserve1;

    /** 备用字段2 */
    private String reserve2;

    /** 场站名称（JOIN station_info，非数据库字段） */
    @TableField(exist = false)
    private String stationName;
}

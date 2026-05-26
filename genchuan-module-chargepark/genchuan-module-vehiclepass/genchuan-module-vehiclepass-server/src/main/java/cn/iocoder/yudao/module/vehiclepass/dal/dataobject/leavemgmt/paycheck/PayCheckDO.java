package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.paycheck;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 缴费核验 DO
 *
 * @author 亘川智城
 */
@TableName("pay_check")
@KeySequence("pay_check_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayCheckDO extends BaseDO {

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
     * 停车费用
     */
    private BigDecimal parkFee;
    /**
     * 缴费状态：已缴清/欠费 关联字典pay_check_status
     */
    private String status;
    /**
     * 核验时间
     */
    private LocalDateTime checkTime;
    /**
     * 场站ID，关联场站表
     */
    private Long stationId;
    /**
     * 场站名称
     */
    @TableField(exist = false)
    private String stationName;
    /**
     * 核验人ID，关联system_user用户表
     */
    private Long checkUserId;
    /**
     * 核验结果
     */
    private String checkResult;
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
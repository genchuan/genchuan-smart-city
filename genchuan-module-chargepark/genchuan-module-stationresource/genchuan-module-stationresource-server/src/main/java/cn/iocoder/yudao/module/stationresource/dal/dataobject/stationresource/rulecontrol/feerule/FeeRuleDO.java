package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.feerule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 收费规则 DO
 *
 * @author 亘川智城
 */
@TableName("fee_rule")
@KeySequence("fee_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeeRuleDO extends BaseDO {

    /**
     * [主键ID] 主键，BIGINT，自增
     */
    @TableId
    private Long id;
    /**
     * [所属场站] 关联场站信息表 station_info.id，必填
     */
    private Long stationId;
    /**
     * [费率类型] 如：停车收费/充电收费/混合收费
     */
    private String rateType;
    /**
     * [免费时长] 单位分钟
     */
    private Integer freeTime;
    /**
     * [计费单位] 如：小时/15分钟/次
     */
    private String chargeUnit;
    /**
     * [状态] 如：待生效/已生效/已禁用
     */
    private String status;
    /**
     * [审核时间]
     */
    private LocalDateTime auditTime;
    /**
     * [审核人] 关联芋道用户表 system_user.id
     */
    private Long auditUserId;
    /**
     * [订单匹配率] 默认0
     */
    private BigDecimal matchRate;
    /**
     * [首小时价格]
     */
    private BigDecimal firstHourPrice;
    /**
     * [后续阶梯价格]varchar存储
     */
    private String stepPrice;
    /**
     * [封顶价格]
     */
    private BigDecimal maxPrice;
    /**
     * [峰谷电价配置] varchar存储
     */
    private String peakValleyConfig;
    /**
     * [会员优惠配置]varchar存储
     */
    private String memberConfig;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [备用字段1]
     */
    private String reserve1;
    /**
     * [备用字段2]
     */
    private String reserve2;


}

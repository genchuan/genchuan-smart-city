package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.chargeparklink;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 充停联动 DO
 *
 * @author 亘川智城
 */
@TableName("charge_park_link")
@KeySequence("charge_park_link_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeParkLinkDO extends BaseDO {

    /**
     * [主键ID] 主键，自增
     */
    @TableId
    private Long id;
    /**
     * [所属场站] 关联场站信息表 station_info
     */
    private Long stationId;
    /**
     * [优惠类型] 如：停车减免/充电减免/费用合并
     */
    private String discountType;
    /**
     * [优惠幅度] 单位 %
     */
    private BigDecimal discount;
    /**
     * [适用车型] 如：小型车/中型车/大型车/新能源车
     */
    private String carType;
    /**
     * [状态] 如：待生效/已生效/已禁用
     */
    private String status;
    /**
     * [审核时间] 审核通过的时间
     */
    private LocalDateTime auditTime;
    /**
     * [审核人] 关联芋道用户表 system_user
     */
    private Long auditUserId;
    /**
     * [今日订单量] 当日订单数量
     */
    private Integer todayOrderCount;
    /**
     * [今日营收] 当日营收金额
     */
    private BigDecimal todayIncome;
    /**
     * [支付率] 支付成功率，单位 %
     */
    private BigDecimal payRate;
    /**
     * [备注] 扩展说明
     */
    private String remark;
    /**
     * [备用字段1] 预留扩展
     */
    private String reserve1;
    /**
     * [备用字段2] 预留扩展
     */
    private String reserve2;


}

package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.depositplan;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 押金方案 DO
 *
 * @author 亘川智城
 */
@TableName("deposit_plan")
@KeySequence("deposit_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositPlanDO extends BaseDO {

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
     * [押金金额] 单位：元
     */
    private BigDecimal depositAmount;
    /**
     * [适用场景] 如：预约停车/预约充电/临时停车
     */
    private String scene;
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
     * [押金订单量] 产生押金的订单总数
     */
    private Integer depositOrderCount;
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

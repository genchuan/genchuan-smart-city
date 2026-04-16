package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.debtexpand;

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
 * 联合追缴拓场配置 DO
 *
 * @author 亘川智城
 */
@TableName("debt_expand")
@KeySequence("debt_expand_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebtExpandDO extends BaseDO {

    /**
     * [主键ID] 主键ID
     */
    @TableId
    private Long id;
    /**
     * [合作场站] 关联场站信息表 station_info
     */
    private Long stationId;
    /**
     * [合作类型] 如：社会停车场拓场/联合追缴
     */
    private String type;
    /**
     * [追缴范围] 如：本区域/跨区域/全平台
     */
    // 🔥 关键修复：给关键字字段加反引号
    @TableField("`range`")
    private String range;
    /**
     * [拓场进度] 单位：%
     */
    private Integer progress;
    /**
     * [状态] 如：未生效/已生效/已禁用
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
     * [完成时间] 拓场完成时间
     */
    private LocalDateTime finishTime;
    /**
     * [追缴完成率] 追缴完成比例
     */
    private BigDecimal recoveryRate;
    /**
     * [备注] 补充说明
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

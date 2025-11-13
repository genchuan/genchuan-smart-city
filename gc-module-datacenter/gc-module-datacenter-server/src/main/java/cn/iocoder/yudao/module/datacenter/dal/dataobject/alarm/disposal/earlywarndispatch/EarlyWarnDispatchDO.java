package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarndispatch;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警告警派单 DO
 *
 * @author zcq
 */
@TableName("biz_early_warn_dispatch")
@KeySequence("biz_early_warn_dispatch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnDispatchDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 派单ID
     */
    private String dispatchId;
    /**
     * 派单编号
     */
    private String dispatchNo;
    /**
     * 预警ID
     */
    private String earlyWarnId;
    /**
     * 预警名称
     */
    private String earlyWarnName;
    /**
     * 派单类型
     */
    private String dispatchType;
    /**
     * 接收部门ID
     */
    private String receiveDeptId;
    /**
     * 接收部门名称
     */
    private String receiveDeptName;
    /**
     * 处置人ID
     */
    private String handleUserId;
    /**
     * 处置人姓名
     */
    private String handleUserName;
    /**
     * 派单状态
     */
    private String dispatchStatus;
    /**
     * 处置时限
     */
    private Integer handleLimit;
    /**
     * 派单时间
     */
    private LocalDateTime dispatchTime;
    /**
     * 接收时间
     */
    private LocalDateTime receiveTime;
    /**
     * 拒单原因
     */
    private String rejectReason;
    /**
     * 派单人ID
     */
    private String dispatchUserId;
    /**
     * 派单人姓名
     */
    private String dispatchUserName;
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
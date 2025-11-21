package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnrelease;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警告警解除 DO
 *
 * @author zcq
 */
@TableName("biz_early_warn_release")
@KeySequence("biz_early_warn_release_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnReleaseDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 解除ID
     */
    private String releaseId;
    /**
     * 预警ID
     */
    private String earlyWarnId;
    /**
     * 预警名称
     */
    private String earlyWarnName;
    /**
     * 解除申请人ID
     */
    private String applyUserId;
    /**
     * 解除申请人姓名
     */
    private String applyUserName;
    /**
     * 解除审核人ID
     */
    private String auditUserId;
    /**
     * 解除审核人姓名
     */
    private String auditUserName;
    /**
     * 解除状态
     */
    private String releaseStatus;
    /**
     * 解除原因
     */
    private String releaseReason;
    /**
     * 申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 驳回原因
     */
    private String rejectReason;
    /**
     * 是否复发
     */
    private String isRecurrence;
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
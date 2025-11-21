package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnhandletrace;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警告警处置跟踪 DO
 *
 * @author zcq
 */
@TableName("biz_early_warn_handle_trace")
@KeySequence("biz_early_warn_handle_trace_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnHandleTraceDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 跟踪ID
     */
    private String handleTraceId;
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
     * 处置进度
     */
    private String handleProgress;
    /**
     * 进度描述
     */
    private String progressDesc;
    /**
     * 操作人ID
     */
    private String operUserId;
    /**
     * 操作人姓名
     */
    private String operUserName;
    /**
     * 操作时间
     */
    private LocalDateTime operTime;
    /**
     * 附件URL
     */
    private String attachUrl;
    /**
     * 是否关键节点
     */
    private String isKeyNode;
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
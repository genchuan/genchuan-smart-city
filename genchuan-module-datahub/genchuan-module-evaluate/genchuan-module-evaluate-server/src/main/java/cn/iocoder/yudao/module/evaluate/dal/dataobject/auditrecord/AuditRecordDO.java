package cn.iocoder.yudao.module.evaluate.dal.dataobject.auditrecord;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 评价结果审核 DO
 *
 * @author 亘川智城
 */
@TableName("eval_audit_record")
@KeySequence("eval_audit_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditRecordDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 审核UUID（主键，UUID）
     */
    private String auditId;
    /**
     * 审核编号
     */
    private String code;
    /**
     * 关联评价任务ID（关联eval_task.task_id）
     */
    private String taskId;
    /**
     * 评价对象ID（关联eval_object.object_id）
     */
    private String objectId;
    /**
     * 评价得分
     */
    private BigDecimal evalScore;
    /**
     * 评价标准ID（关联eval_standard_item.standard_item_id）
     */
    private String standardId;
    /**
     * 审核状态（关联sys_audit_status.status_id）
     */
    private String status;
    /**
     * 业务创建时间（创建时间）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 审核人（关联sys_user.user_id）
     */
    private String auditBy;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 数据来源汇总
     */
    private String dataSource;
    /**
     * 否决项检查结果（通过/触发）
     */
    private String rejectCheckResult;
    /**
     * 驳回原因完整内容
     */
    private String rejectReason;
    /**
     * 审核意见摘要
     */
    private String auditOpinion;
    /**
     * 数据来源明细
     */
    private String dataSourceDetail;
    /**
     * 各指标得分
     */
    private String indexScore;
    /**
     * 分配审核人（关联sys_user.user_id）
     */
    private String assignBy;
    /**
     * 任务创建人（关联sys_user.user_id）
     */
    private String taskCreateBy;
    /**
     * 待审核时长（小时）
     */
    private BigDecimal waitHour;
    /**
     * 问题数据来源
     */
    private String errorDataSource;
    /**
     * 重算次数
     */
    private Integer recalcCount;
    /**
     * 最后重算时间
     */
    private LocalDateTime recalcTime;
    /**
     * 修正指引
     */
    private String correctGuide;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}
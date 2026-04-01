package cn.iocoder.yudao.module.evaluate.dal.dataobject.appealrecord;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 申诉复核 DO
 *
 * @author 亘川智城
 */
@TableName("eval_appeal_record")
@KeySequence("eval_appeal_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppealRecordDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 申诉UUID（主键，UUID）
     */
    private String appealId;
    /**
     * 申诉编号
     */
    private String code;
    /**
     * 申诉对象ID（关联eval_object.object_id）
     */
    private String objectId;
    /**
     * 关联公示记录ID（关联eval_public_record.public_id）
     */
    private String publicId;
    /**
     * 关联审核记录ID（关联eval_audit_record.audit_id）
     */
    private String auditId;
    /**
     * 申诉人（关联sys_user.user_id）
     */
    private String appealBy;
    /**
     * 申诉提交时间
     */
    private LocalDateTime submitTime;
    /**
     * 复核状态（关联sys_appeal_status.status_id）
     */
    private String status;
    /**
     * 原评价得分
     */
    private BigDecimal originalScore;
    /**
     * 复核人员ID（关联sys_user.user_id，多个用逗号分隔）
     */
    private String reviewBy;
    /**
     * 复核完成时间
     */
    private LocalDateTime reviewTime;
    /**
     * 最终复核结果（维持原结果/修正结果）
     */
    private String finalResult;
    /**
     * 修正后得分
     */
    private BigDecimal correctScore;
    /**
     * 修正后标准ID（关联eval_standard_item.standard_item_id）
     */
    private String correctStandardId;
    /**
     * 结案状态（未结案/已结案）
     */
    private String closeStatus;
    /**
     * 结案人（关联sys_user.user_id）
     */
    private String closeBy;
    /**
     * 结案时间
     */
    private LocalDateTime closeTime;
    /**
     * 申诉理由摘要
     */
    private String appealReason;
    /**
     * 证明材料数量
     */
    private Integer fileCount;
    /**
     * 待受理时长（小时）
     */
    private BigDecimal waitHour;
    /**
     * 申诉类型
     */
    private String appealType;
    /**
     * 原评价等级
     */
    private String originalGrade;
    /**
     * 材料审核状态
     */
    private String fileCheckStatus;
    /**
     * 驳回原因完整内容
     */
    private String rejectReason;
    /**
     * 证明材料审核结果
     */
    private String fileCheckResult;
    /**
     * 驳回通知推送状态（已推送/未推送）
     */
    private String notifyStatus;
    /**
     * 受理中时长（小时）
     */
    private BigDecimal reviewHour;
    /**
     * 复核进度
     */
    private String reviewProgress;
    /**
     * 阶段性核查结果
     */
    private String stageResult;
    /**
     * 最新操作时间
     */
    private LocalDateTime latestOperTime;
    /**
     * 复核超期预警（正常/超期预警）
     */
    private String warningStatus;
    /**
     * 复核初步结果
     */
    private String preResult;
    /**
     * 核查材料数量
     */
    private Integer checkFileCount;
    /**
     * 数据修正建议
     */
    private String correctSuggest;
    /**
     * 结案报告下载链接
     */
    private String closeReportUrl;
    /**
     * 数据同步状态（已同步/未同步/同步中）
     */
    private String dataSyncStatus;
    /**
     * 同步完成时间
     */
    private LocalDateTime syncTime;
    /**
     * 申诉人反馈状态（已反馈/未反馈）
     */
    private String feedbackStatus;
    /**
     * 关联存档记录ID（关联eval_archive_record.archive_id）
     */
    private String archiveId;
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
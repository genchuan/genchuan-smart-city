package cn.iocoder.yudao.module.evaluate.dal.dataobject.pushrecord;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 结果推送记录 DO
 *
 * @author 亘川智城
 */
@TableName("eval_push_record")
@KeySequence("eval_push_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushRecordDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 推送UUID（主键，UUID）
     */
    private String pushId;
    /**
     * 推送编号
     */
    private String code;
    /**
     * 关联存档记录ID（关联eval_archive_record.archive_id）
     */
    private String archiveId;
    /**
     * 关联推送目标ID（关联sys_push_target.target_id）
     */
    private String targetId;
    /**
     * 关联推送方式ID（关联eval_push_type.type_id）
     */
    private String typeId;
    /**
     * 推送状态（关联sys_push_status.status_id）
     */
    private String status;
    /**
     * 推送内容摘要
     */
    private String content;
    /**
     * 创建人（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 业务创建时间（创建时间）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 推送次数
     */
    private Integer pushCount;
    /**
     * 最新推送时间
     */
    private LocalDateTime latestPushTime;
    /**
     * 失败原因
     */
    private String failReason;
    /**
     * 接收方反馈状态（已反馈/未反馈/无需反馈）
     */
    private String feedbackStatus;
    /**
     * 数据同步量
     */
    private Integer dataSyncNum;
    /**
     * 推送地址
     */
    private String targetAddr;
    /**
     * 待推送原因
     */
    private String waitReason;
    /**
     * 待推送时长（小时）
     */
    private BigDecimal waitHour;
    /**
     * 数据完整性校验结果（已通过/未通过）
     */
    private String dataCheckResult;
    /**
     * 推送内容格式（JSON/Excel/文本）
     */
    private String contentFormat;
    /**
     * 目标系统状态（正常/维护/异常）
     */
    private String targetStatus;
    /**
     * 接收方响应
     */
    private String receiverResp;
    /**
     * 重新推送次数
     */
    private Integer repushCount;
    /**
     * 最新重新推送时间
     */
    private LocalDateTime latestRepushTime;
    /**
     * 接收方反馈内容摘要
     */
    private String feedbackContent;
    /**
     * 推送日志链接
     */
    private String logUrl;
    /**
     * 数据一致性校验结果（已核对/未核对/一致/不一致）
     */
    private String dataConsistResult;
    /**
     * 失败类型ID（关联sys_dock_fail_type.type_id）
     */
    private String failTypeId;
    /**
     * 目标系统错误码
     */
    private String errorCode;
    /**
     * 修正方案建议
     */
    private String fixSuggest;
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
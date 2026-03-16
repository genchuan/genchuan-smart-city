package cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 整改通知书复审台账 DO
 *
 * @author 亘川智城
 */
@TableName("rectify_review")
@KeySequence("rectify_review_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RectifyReviewDO extends BaseDO {

    /**
     * [主键ID] 自增主键
     */
    @TableId
    private Long id;
    /**
     * [台账编号] 整改通知书复审台账唯一编号
     */
    private String ledgerCode;
    /**
     * [企业ID] 关联park_enterprise_info.id
     */
    private Long entId;
    /**
     * [违规类型ID] 关联park_illegal_type_dict.id
     */
    private Long illegalTypeId;
    /**
     * [违规等级ID] 关联park_illegal_level_dict.id
     */
    private Long illegalLevelId;
    /**
     * [违规证据链接] 多链接以英文逗号分隔，varchar类型
     */
    private String evidenceUrl;
    /**
     * [草拟时间] 整改通知书草拟时间
     */
    private LocalDateTime draftTime;
    /**
     * [复审状态] 如：待复审/已下发/已撤销
     */
    private String reviewStatus;
    /**
     * [复审人ID] 关联park_user.id
     */
    private Long reviewBy;
    /**
     * [复审时间] 实际复审操作时间
     */
    private LocalDateTime reviewTime;
    /**
     * [撤销时间] 仅当状态为已撤销时有值
     */
    private LocalDateTime cancelTime;
    /**
     * [撤销原因ID] 关联park_cancel_reason_dict.id，仅已撤销状态赋值
     */
    private Long cancelReasonId;
    /**
     * [执法复审台账编号] 关联park_law_review_ledger.ledger_code
     */
    private String lawLedgerCode;


//    "整改通知书编号"
    private String rectifyNoticeCode;
    /**
     * [通用扩展字段1] 预留
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 预留
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 预留
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 预留
     */
    private String extCommon4;

}

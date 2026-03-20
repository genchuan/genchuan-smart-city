package cn.iocoder.yudao.module.kitchen.dal.dataobject.punishreviewledger;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 处罚通知书复审台账 DO
 *
 * @author 亘川智城
 */
@TableName("punish_review_ledger")
@KeySequence("punish_review_ledger_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PunishReviewLedgerDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [台账编号] 唯一编号
     */
    private String ledgerCode;
    /**
     * [企业ID] 关联enterprise_info.id
     */
    private Long entId;
    /**
     * [违规类型ID] 关联illegal_type_dict.id
     */
    private Long illegalTypeId;
    /**
     * [违规等级ID] 关联illegal_level_dict.id
     */
    private Long illegalLevelId;
    /**
     * [企业整改记录id]
     */
    private Long entRectifyRecordId;
    /**
     * [处罚通知书id]
     */
    private Long punishNoticeId;
    /**
     * [执法复审台账编号] 关联law_review_ledger.ledger_code
     */
    private String lawLedgerCode;
    /**
     * [违规证据链接] JSON字符串格式，可多链接
     */
    private String evidenceUrl;
    /**
     * [草拟处罚金额] 单位：元
     */
    private BigDecimal draftPunishAmt;
    /**
     * [处罚法律依据]
     */
    private String legalBasis;
    /**
     * [复审状态] 如：待复审/已下发/已撤销
     */
    private String reviewStatus;
    /**
     * [复审人] 关联park_user.id
     */
    private Long reviewBy;
    /**
     * [撤销原因ID] 关联cancel_reason_dict.id，仅已撤销状态赋值
     */
    private Long cancelReasonId;
    /**
     * [草拟时间]
     */
    private LocalDateTime draftTime;
    /**
     * [复审时间]
     */
    private LocalDateTime reviewTime;
    /**
     * [撤销时间]
     */
    private LocalDateTime cancelTime;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}

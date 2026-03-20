package cn.iocoder.yudao.module.kitchen.dal.dataobject.punishnotice;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 处罚通知书 DO
 *
 * @author 亘川智城
 */
@TableName("punish_notice")
@KeySequence("punish_notice_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PunishNoticeDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [处罚通知书编号] 唯一编号
     */
    private String noticeCode;
    /**
     * [处罚复审台账ID] 关联punish_review_ledger.id，唯一
     */
    private Long punishReviewId;
    /**
     * [企业缴费记录表id]
     */
    private Long entPayRecordId;
    /**
     * [下发时间]
     */
    private LocalDateTime issueTime;
    /**
     * [送达时间]
     */
    private LocalDateTime receiveTime;
    /**
     * [缴款期限]
     */
    private LocalDateTime payDeadline;
    /**
     * [送达状态] 如：未送达/已送达/拒收
     */
    private String receiveStatus;
    /**
     * [实际处罚金额] 单位：元
     */
    private BigDecimal actualPunishAmt;
    /**
     * [处罚决定书原件内容] 富文本
     */
    private String decisionContent;
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

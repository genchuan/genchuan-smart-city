package cn.iocoder.yudao.module.kitchen.dal.dataobject.lawreviewledger;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 执法复审总台账 DO
 *
 * @author 亘川智城
 */
@TableName("law_review_ledger")
@KeySequence("law_review_ledger_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LawReviewLedgerDO extends BaseDO {

    /**
     * [主键ID] 执法复审总台账唯一标识
     */
    @TableId
    private Long id;
    /**
     * [执法复审台账编号] 唯一，按执法区域编码+年份+序号自动生成
     */
    private String ledgerCode;
    /**
     * [整改复审台账ID] 关联park_rectify_review.id，与punish_review_id互斥
     */
    private Long rectifyReviewId;
    /**
     * [处罚复审台账ID] 关联park_punish_review_ledger.id，与rectify_review_id互斥
     */
    private Long punishReviewId;
    /**
     * [企业ID] 关联park_enterprise_info.id
     */
    private Long entId;
    /**
     * [执法区域编码] 关联area_dict.area_code
     */
    private String lawAreaCode;
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

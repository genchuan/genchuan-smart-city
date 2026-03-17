package cn.iocoder.yudao.module.kitchen.dal.dataobject.entrectifyrecord;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 企业整改记录 DO
 *
 * @author 亘川智城
 */
@TableName("ent_rectify_record")
@KeySequence("ent_rectify_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntRectifyRecordDO extends BaseDO {

    /**
     * [主键ID] 企业整改记录唯一标识
     */
    @TableId
    private Long id;

    private String uniCode;
    /**
     * [处罚复审台账id]
     */
    private Long punishReviewId;
    /**
     * [整改复审台账id]
     */
    private Long rectifyReviewId;
    /**
     * [整改通知书ID] 关联park_rectify_notice.id，唯一
     */
    private Long rectifyNoticeId;
    /**
     * [企业ID] 关联park_enterprise_info.id
     */
    private Long entId;
    /**
     * [整改状态] 如：未整改/整改中/已完成/整改不合格
     */
    private String rectifyStatus;
    /**
     * [整改完成时间] datetime格式，仅当状态为已完成或整改不合格时有值
     */
    private LocalDateTime rectifyCompleteTime;
    /**
     * [企业整改说明] 富文本内容，可为空
     */
    private String rectifyDesc;
    /**
     * [整改佐证证据链接] JSON格式varchar，如[{"name":"xxx.png","type":"image","url":"xx.png"}]，可为空
     */
    private String rectifyEvidenceUrl;
    /**
     * [整改审核结果] 如：合格/不合格，可为空
     */
    private String auditResult;
    /**
     * [整改审核人ID] 关联park_user.id，可为空
     */
    private Long auditBy;
    /**
     * [整改审核驳回原因] 文本，可为空
     */
    private String rejectReason;
    /**
     * [整改审核时间] 可为空
     */
    private LocalDateTime auditTime;
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

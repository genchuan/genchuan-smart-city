package cn.iocoder.yudao.module.evaluate.dal.dataobject.archiverecord;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 评价结果存档 DO
 *
 * @author 亘川智城
 */
@TableName("eval_archive_record")
@KeySequence("eval_archive_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveRecordDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 存档UUID（主键，UUID）
     */
    private String archiveId;
    /**
     * 存档编号
     */
    private String code;
    /**
     * 关联公示记录ID（关联eval_public_record.public_id）
     */
    private String publicId;
    /**
     * 评价对象ID（关联eval_object.object_id）
     */
    private String objectId;
    /**
     * 关联审核记录ID（关联eval_audit_record.audit_id）
     */
    private String auditId;
    /**
     * 评价标准ID（关联eval_standard_item.standard_item_id）
     */
    private String standardId;
    /**
     * 评价得分
     */
    private BigDecimal evalScore;
    /**
     * 存档状态（关联sys_archive_status.status_id）
     */
    private String status;
    /**
     * 申请存档时间
     */
    private LocalDateTime applyTime;
    /**
     * 存档人（关联sys_user.user_id）
     */
    private String archiveBy;
    /**
     * 实际存档时间
     */
    private LocalDateTime actualTime;
    /**
     * 存档附件数量
     */
    private Integer attachmentCount;
    /**
     * 存档存储位置
     */
    private String storeLocation;
    /**
     * 数据溯源链接
     */
    private String traceUrl;
    /**
     * 查询次数
     */
    private Integer queryCount;
    /**
     * 最新查询时间
     */
    private LocalDateTime latestQueryTime;
    /**
     * 待存档原因
     */
    private String waitReason;
    /**
     * 待存档时长（小时）
     */
    private BigDecimal waitHour;
    /**
     * 存档条件校验结果（已满足/未满足）
     */
    private String checkResult;
    /**
     * 关联申诉记录ID（关联eval_appeal_record.appeal_id）
     */
    private String appealId;
    /**
     * 申诉复核状态
     */
    private String appealStatus;
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
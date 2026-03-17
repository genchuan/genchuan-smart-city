package cn.iocoder.yudao.module.evaluate.dal.dataobject.publicrecord;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 评价结果公示 DO
 *
 * @author 亘川智城
 */
@TableName("eval_public_record")
@KeySequence("eval_public_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicRecordDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 公示UUID（主键，UUID）
     */
    private String publicId;
    /**
     * 公示编号
     */
    private String code;
    /**
     * 关联审核记录ID（关联eval_audit_record.audit_id）
     */
    private String auditId;
    /**
     * 评价对象ID（关联eval_object.object_id）
     */
    private String objectId;
    /**
     * 评价标准ID（关联eval_standard_item.standard_item_id）
     */
    private String standardId;
    /**
     * 公示开始时间
     */
    private LocalDateTime startTime;
    /**
     * 公示结束时间
     */
    private LocalDateTime endTime;
    /**
     * 公示状态（关联sys_public_status.status_id）
     */
    private String status;
    /**
     * 公示创建人（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 业务创建时间（创建时间）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 公示链接
     */
    private String publicUrl;
    /**
     * 异议数量
     */
    private Integer objectionCount;
    /**
     * 公示完成时间
     */
    private LocalDateTime completeTime;
    /**
     * 关联申诉记录编号
     */
    private String appealRecordCode;
    /**
     * 公示剩余时长（小时）
     */
    private BigDecimal remainHour;
    /**
     * 公示访问量
     */
    private Long visitCount;
    /**
     * 最新异议时间
     */
    private LocalDateTime latestObjectionTime;
    /**
     * 公示发布渠道
     */
    private String publishChannel;
    /**
     * 管理员联系方式
     */
    private String adminContact;
    /**
     * 终止原因
     */
    private String stopReason;
    /**
     * 终止时间
     */
    private LocalDateTime stopTime;
    /**
     * 终止人（关联sys_user.user_id）
     */
    private String stopBy;
    /**
     * 公示终止时剩余时长（小时）
     */
    private BigDecimal stopRemainHour;
    /**
     * 终止前异议数量
     */
    private Integer stopObjectionCount;
    /**
     * 后续处理建议
     */
    private String followSuggest;
    /**
     * 历史公示链接状态（已失效/可查看）
     */
    private String urlStatus;
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
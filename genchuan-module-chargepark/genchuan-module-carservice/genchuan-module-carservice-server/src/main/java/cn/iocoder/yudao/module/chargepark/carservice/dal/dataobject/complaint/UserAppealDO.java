package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用户申诉 DO
 *
 * 数据库表：user_appeal
 *
 * @author carservice
 */
@TableName("user_appeal")
@KeySequence("user_appeal_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAppealDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 用户 ID，关联芋道用户表 system_user
     */
    private Long userId;
    /**
     * 订单 ID，关联订单模块订单表
     */
    private Long orderId;
    /**
     * 申诉内容
     */
    private String content;
    /**
     * 提交时间
     */
    private LocalDateTime submitTime;
    /**
     * 申诉状态：待审核/待处置/已完成
     * 关联字典 user_appeal_status
     */
    private String status;
    /**
     * 审核人 ID，关联芋道用户表 system_user
     */
    private Long auditUserId;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 处置人 ID，关联芋道用户表 system_user
     */
    private Long handleUserId;
    /**
     * 处置时间，执行认领的时刻
     */
    private LocalDateTime handleTime;
    /**
     * 处置进度
     */
    private String progress;
    /**
     * 反馈内容
     */
    private String feedbackContent;
    /**
     * 反馈时间
     */
    private LocalDateTime feedbackTime;
    /**
     * 审核备注
     */
    private String auditRemark;
    /**
     * 驳回理由
     */
    private String rejectReason;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;

}

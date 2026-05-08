package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 意见建议 DO
 *
 * 数据库表：suggestion
 *
 * @author carservice
 */
@TableName("suggestion")
@KeySequence("suggestion_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionDO extends BaseDO {

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
     * 意见内容
     */
    private String content;
    /**
     * 提交时间
     */
    private LocalDateTime submitTime;
    /**
     * 处理状态：待处理/处理中/已完成
     * 关联字典 suggestion_status
     */
    private String status;
    /**
     * 处理人 ID，关联芋道用户表 system_user
     */
    private Long handleUserId;
    /**
     * 处理时间，认领的时刻
     */
    private LocalDateTime handleTime;
    /**
     * 处理进度
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
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;

}

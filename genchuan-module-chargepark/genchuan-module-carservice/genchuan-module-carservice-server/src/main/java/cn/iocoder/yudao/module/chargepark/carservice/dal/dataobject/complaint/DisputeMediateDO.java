package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 纠纷调解 DO
 *
 * 数据库表：dispute_mediate
 *
 * @author carservice
 */
@TableName("dispute_mediate")
@KeySequence("dispute_mediate_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisputeMediateDO extends BaseDO {

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
     * 商户 ID，关联商户模块商户表
     */
    private Long merchantId;
    /**
     * 纠纷内容
     */
    private String content;
    /**
     * 发起时间
     */
    private LocalDateTime submitTime;
    /**
     * 调解状态：待调解/调解中/已完成
     * 关联字典 dispute_mediate_status
     */
    private String status;
    /**
     * 调解人 ID，关联芋道用户表 system_user
     */
    private Long mediateUserId;
    /**
     * 调解进度
     */
    private String progress;
    /**
     * 确认时间，记录双方确认调解结果的时间
     */
    private LocalDateTime confirmTime;
    /**
     * 调解确认结果
     */
    private String confirmResult;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;

}

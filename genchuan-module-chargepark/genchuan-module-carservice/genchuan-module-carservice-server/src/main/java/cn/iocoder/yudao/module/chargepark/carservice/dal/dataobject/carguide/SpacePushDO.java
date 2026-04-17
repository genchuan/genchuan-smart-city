package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 空位推送 DO
 *
 * 数据库表：space_push
 *
 * @author carservice
 */
@TableName("space_push")
@KeySequence("space_push_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpacePushDO extends BaseDO {

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
     * 场站 ID，关联场站模块场站表
     */
    private Long stationId;
    /**
     * 空位信息（如车位编号、剩余时长等）
     */
    private String spaceInfo;
    /**
     * 推送时间
     */
    private LocalDateTime pushTime;
    /**
     * 推送状态：待推送/已推送
     * 关联字典 space_push_status
     */
    private String status;
    /**
     * 推送结果：成功/失败
     * 关联字典 space_push_push_result
     */
    private String pushResult;
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

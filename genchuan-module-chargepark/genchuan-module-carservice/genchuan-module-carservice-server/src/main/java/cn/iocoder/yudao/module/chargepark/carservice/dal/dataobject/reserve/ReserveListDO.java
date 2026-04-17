package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.reserve;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预约列表 DO
 *
 * 数据库表：reserve_list
 *
 * @author carservice
 */
@TableName("reserve_list")
@KeySequence("reserve_list_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReserveListDO extends BaseDO {

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
     * 车位 ID，关联车位模块车位表
     */
    private Long spaceId;
    /**
     * 预约时间，用户预约的使用时间
     */
    private LocalDateTime reserveTime;
    /**
     * 预约类型：停车预约/充电预约
     * 关联字典 reserve_list_reserve_type
     */
    private String reserveType;
    /**
     * 预约状态：待审核/已生效/已完成/已取消
     * 关联字典 reserve_list_status
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
     * 完成时间
     */
    private LocalDateTime finishTime;
    /**
     * 评价得分，1-5 分
     */
    private Integer score;
    /**
     * 审核备注
     */
    private String auditRemark;
    /**
     * 驳回理由
     */
    private String rejectReason;
    /**
     * 评价内容
     */
    private String evaluateContent;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;

}

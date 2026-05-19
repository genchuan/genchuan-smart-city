package cn.iocoder.yudao.module.accessmgmt.dal.dataobject.visitormgmt.visitoraccess;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 访客通行 DO
 *
 * @author 亘川智城
 */
@TableName("visitor_access")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitorAccessDO extends BaseDO {

    /**
     * [主键ID] 主键
     */
    @TableId
    private Long id;
    /**
     * [预约ID] 关联的访客预约ID
     */
    private Long appointId;
    /**
     * [访客姓名] 访客姓名
     */
    private String visitorName;
    /**
     * [通行区域] 访客通行区域（如大门/楼栋/楼层）
     */
    private String accessArea;
    /**
     * [通行时间] 实际通行时间
     */
    private LocalDateTime accessTime;
    /**
     * [凭证状态] 通行凭证状态
     */
    private String ticketStatus;
    /**
     * [通行状态] 如：已放行/已禁行
     */
    private String accessStatus;
    /**
     * [核验结果] 核验结果或禁行原因
     */
    private String checkResult;
    /**
     * [授权有效期] 通行授权截止时间
     */
    private LocalDateTime authValidity;
    /**
     * [经办人] 经办人
     */
    private String handleUser;
    /**
     * [备用字段1] 提醒内容
     */
    private String reserve1;
    /**
     * [备用字段2] 备用字段2
     */
    private String reserve2;

}

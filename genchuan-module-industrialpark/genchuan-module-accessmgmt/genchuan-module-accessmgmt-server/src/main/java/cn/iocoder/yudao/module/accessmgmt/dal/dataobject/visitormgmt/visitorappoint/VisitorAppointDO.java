package cn.iocoder.yudao.module.accessmgmt.dal.dataobject.visitormgmt.visitorappoint;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.time.LocalDateTime;

@TableName("visitor_appoint")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitorAppointDO extends BaseDO {

    /**
     * [主键ID] 主键
     */
    @TableId
    private Long id;
    /**
     * [访客姓名] 访客姓名
     */
    private String visitorName;
    /**
     * [身份证号] 访客身份证号
     */
    private String idCard;
    /**
     * [被访企业] 被访企业名称
     */
    private String visitCompany;
    /**
     * [到访时间] 预约到访时间
     */
    private LocalDateTime visitTime;
    /**
     * [预约状态] 如:待审核/已通过/已驳回/已取消/已离园
     */
    private String appointStatus;
    /**
     * [通行凭证] 审核通过后生成的通行凭证号
     */
    private String ticket;
    /**
     * [到达时间] 实际到达时间
     */
    private LocalDateTime arriveTime;
    /**
     * [离开时间] 实际离园时间
     */
    private LocalDateTime leaveTime;
    /**
     * [审核人] 审核人
     */
    private String checkUser;
    /**
     * [审核结果] 审核结果
     */
    private String checkResult;
    /**
     * [驳回原因] 驳回原因
     */
    private String rejectReason;
    /**
     * [备用字段1] 备用字段1
     */
    private String reserve1;
    /**
     * [备用字段2] 备用字段2
     */
    private String reserve2;

}

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

    @TableId
    private Long id;
    private String visitorName;
    private String idCard;
    private String visitCompany;
    private LocalDateTime visitTime;
    private String appointStatus;
    private String ticket;
    private LocalDateTime arriveTime;
    private LocalDateTime leaveTime;
    private String checkUser;
    private String checkResult;
    private String rejectReason;
    private String reserve1;
    private String reserve2;

}

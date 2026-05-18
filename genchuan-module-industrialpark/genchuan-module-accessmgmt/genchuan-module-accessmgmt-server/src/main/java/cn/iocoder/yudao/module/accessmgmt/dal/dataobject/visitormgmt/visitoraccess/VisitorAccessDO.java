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

    @TableId
    private Long id;
    private Long appointId;
    private String visitorName;
    private String accessArea;
    private LocalDateTime accessTime;
    private String ticketStatus;
    private String accessStatus;
    private String checkResult;
    private LocalDateTime authValidity;
    private String handleUser;
    private String reserve1;
    private String reserve2;

}

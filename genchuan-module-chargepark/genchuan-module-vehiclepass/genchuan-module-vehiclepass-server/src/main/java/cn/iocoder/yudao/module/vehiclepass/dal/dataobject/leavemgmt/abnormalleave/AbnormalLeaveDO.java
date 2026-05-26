package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.abnormalleave;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 异常离场 DO
 *
 * @author 亘川智城
 */
@TableName("abnormal_leave")
@KeySequence("abnormal_leave_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbnormalLeaveDO extends BaseDO {

    @TableId
    private Long id;

    private String plateNo;

    private String abnormalType;

    private LocalDateTime identifyTime;

    private String status;

    private Long stationId;

    @TableField(exist = false)
    private String stationName;

    private Long handleUserId;

    private String handleType;

    private LocalDateTime handleTime;

    private String handleProgress;

    private String ignoreReason;

    private String remark;

    private String reserve1;

    private String reserve2;

}
package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.leavehandle;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 离校办理 DO
 *
 * @author 芋道源码
 */
@TableName("leave_handle")
@KeySequence("leave_handle_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveHandleDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 学生 ID
     */
    private Long studentId;
    /**
     * 离校时间
     */
    private LocalDateTime leaveTime;
    /**
     * 离校去处
     */
    private String leaveAddress;
    /**
     * 家长确认时间
     */
    private LocalDateTime parentConfirmTime;
    /**
     * 办理人
     */
    private String handleUser;
    /**
     * 办理时间
     */
    private LocalDateTime handleTime;
    /**
     * 退宿时间
     */
    private LocalDateTime checkoutTime;
    /**
     * 退宿状态：未退宿/已退宿
     */
    private String checkoutStatus;
    /**
     * 办理完成率
     */
    private BigDecimal finishRate;
    /**
     * 状态：待确认/待办理/已离校
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;


}
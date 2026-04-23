package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.gateopen;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 开闸管理 DO
 *
 * @author 亘川智城
 */
@TableName("gate_open")
@KeySequence("gate_open_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GateOpenDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 场站ID，关联场站表
     */
    private Long stationId;
    /**
     * 开闸原因：紧急通行 / 故障处理 / 其他，关联字典gate_open_open_reason
     */
    private String openReason;
    /**
     * 申请人ID，关联system_user用户表
     */
    private Long applyUserId;
    /**
     * 申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 状态：待审批 / 已通过 / 已驳回 / 已执行，关联字典gate_open_status
     */
    private String status;
    /**
     * 审批人ID，关联system_user用户表
     */
    private Long auditUserId;
    /**
     * 审批时间
     */
    private LocalDateTime auditTime;
    /**
     * 执行时间
     */
    private LocalDateTime executeTime;
    /**
     * 驳回理由
     */
    private String rejectReason;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inspectmgmt.resulthandle;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 结果处置 DO
 *
 * @author 亘川智城
 */
@TableName("result_handle")
@KeySequence("result_handle_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultHandleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联任务ID，关联稽查任务表inspect_task
     */
    private Long taskId;
    /**
     * 违规类型：违规通行/欠费逃费/其他，关联字典result_handle_violation_type
     */
    private String violationType;
    /**
     * 处置方式：补缴费用/限制入场/警告/其他，关联字典result_handle_handle_method
     */
    private String handleMethod;
    /**
     * 处置类型：通过/执行，关联字典result_handle_handle_type
     */
    private String handleType;
    /**
     * 状态：待审核/待处置/已完成/已驳回，关联字典result_handle_status
     */
    private String status;
    /**
     * 片区ID，关联片区表
     */
    private Long areaId;
    /**
     * 处置人ID，关联芋道用户表system_user
     */
    private Long handleUserId;
    /**
     * 处置时间
     */
    private LocalDateTime handleTime;
    /**
     * 整改状态：未整改/已整改，关联字典result_handle_rectify_status
     */
    private String rectifyStatus;
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
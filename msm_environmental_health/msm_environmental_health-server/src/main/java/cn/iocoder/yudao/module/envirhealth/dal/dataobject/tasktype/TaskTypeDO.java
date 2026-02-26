package cn.iocoder.yudao.module.envirhealth.dal.dataobject.tasktype;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 任务类型字典表【通用复用】 DO
 *
 * @author 芋道源码
 */
@TableName("sys_task_type")
@KeySequence("sys_task_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskTypeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String sysTaskTypeId;
    /**
     * 任务类型名称（可选值：保洁任务/收运任务/设施维修任务/问题处置任务/核查任务/监测任务/养护任务/污水处置任务）
     */
    private String name;
    /**
     * 任务类型编码
     */
    private String code;
    /**
     * 状态（可选值：0-禁用/1-启用）
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}
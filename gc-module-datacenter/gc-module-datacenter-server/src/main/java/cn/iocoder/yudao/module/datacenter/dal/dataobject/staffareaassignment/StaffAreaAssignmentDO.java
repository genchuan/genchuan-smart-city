package cn.iocoder.yudao.module.datacenter.dal.dataobject.staffareaassignment;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 人员区域分配 DO
 *
 * @author zcq
 */
@TableName("gc_staff_area_assignment")
@KeySequence("gc_staff_area_assignment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffAreaAssignmentDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分配ID
     */
    private String assignmentId;
    /**
     * 人员ID
     */
    private String staffId;
    /**
     * 人员姓名
     */
    private String staffName;
    /**
     * 区域ID
     */
    private String areaId;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 分配类型
     */
    private String assignmentType;
    /**
     * 分配周期
     */
    private String assignmentCycle;
    /**
     * 生效时间
     */
    private LocalDateTime effectiveTime;
    /**
     * 失效时间
     */
    private LocalDateTime expiryTime;
    /**
     * 分配状态
     */
    private String assignmentStatus;

}
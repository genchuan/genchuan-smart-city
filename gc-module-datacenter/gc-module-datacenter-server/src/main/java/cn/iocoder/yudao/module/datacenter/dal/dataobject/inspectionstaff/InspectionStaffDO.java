package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspectionstaff;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查人员信息 DO
 *
 * @author zcq
 */
@TableName("gc_inspection_staff")
@KeySequence("gc_inspection_staff_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionStaffDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 人员ID
     */
    private String staffId;
    /**
     * 人员姓名
     */
    private String staffName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 所属部门ID
     */
    private Long deptId;
    /**
     * 所属部门名称
     */
    private String deptName;
    /**
     * 人员类型
     */
    private String staffType;
    /**
     * 资质证书路径
     */
    private String qualificationPath;
    /**
     * 作业权限
     */
    private String workPermission;
    /**
     * 入职时间
     */
    private LocalDateTime entryTime;
    /**
     * 离职状态
     */
    private String dimissionStatus;

}
package cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmaintainuser;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 运维人员 DO
 *
 * @author lxs
 */
@TableName("park_maintain_user")
@KeySequence("park_maintain_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkMaintainUserDO extends BaseDO {

    /**
     * [主键ID] 运维人员唯一标识
     */
    @TableId
    private Long id;
    /**
     * [用户ID] 关联 park_user.id
     */
    private Long userId;
    /**
     * [部门ID] 关联 sys_dept.id，所属部门
     */
    private Long deptId;
    /**
     * [岗位类型] 设备维护 / 故障排查 / 工单处理
     */
    private String jobType;
    /**
     * [技能标签] JSON 型 varchar 存储
     */
    private String skillTags;
    /**
     * [值班状态] 在岗 / 休假 / 请假
     */
    private String onDutyStatus;
    /**
     * [备注] 运维人员相关备注说明
     */
    private String remark;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}

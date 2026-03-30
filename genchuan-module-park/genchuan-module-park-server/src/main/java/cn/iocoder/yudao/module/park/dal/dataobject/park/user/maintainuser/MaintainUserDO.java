package cn.iocoder.yudao.module.park.dal.dataobject.park.user.maintainuser;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 运维人员 DO
 *
 * @author 亘川智城
 */
@TableName("park_maintain_user")
@KeySequence("park_maintain_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintainUserDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [用户ID] 关联park_user.id
     */
    private Long userId;
    /**
     * [部门ID] 关联park_dept.id
     */
    private Long deptId;
    /**
     * [岗位类型] 如:设备维护/故障排查/工单处理
     */
    private String jobType;
    /**
     * [技能标签] JSON格式varchar
     */
    private String skillTags;
    /**
     * [值班状态] 如:在岗/休假/请假
     */
    private String onDutyStatus;
    /**
     * [联系电话]
     */
    private String contactPhone;
    /**
     * [备注]
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

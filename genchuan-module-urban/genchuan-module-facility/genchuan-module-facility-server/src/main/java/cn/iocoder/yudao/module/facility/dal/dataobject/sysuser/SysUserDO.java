package cn.iocoder.yudao.module.facility.dal.dataobject.sysuser;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 系统用户 DO
 *
 * @author 亘川智城
 */
@TableName("sys_user")
@KeySequence("sys_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDO extends BaseDO {

    /**
     * [主键ID] 用户唯一标识
     */
    @TableId
    private Long id;
    /**
     * [用户名称]用户姓名
     */
    private String nickname;
    /**
     * [唯一用户名]
     */
    private String username;
    /**
     * [登录账号] 登录账号，需保证业务唯一，必填
     */
    private String accountId;
    /**
     * [角色ID] 用户角色ID，关联sys_role.id，必填
     */
    private Long roleId;
    /**
     * [部门ID] 所属部门ID，关联sys_dept.id
     */
    private Long deptId;
    /**
     * [联系电话] 联系电话
     */
    private String phone;
    /**
     * [账号状态]如:启用/停用
     */
    private String status;
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

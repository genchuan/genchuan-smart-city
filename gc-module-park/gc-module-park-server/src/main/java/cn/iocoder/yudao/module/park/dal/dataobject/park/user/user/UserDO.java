package cn.iocoder.yudao.module.park.dal.dataobject.park.user.user;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 系统用户 DO
 *
 * @author 亘川智城
 */
@TableName("park_user")
@KeySequence("park_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [用户名]
     */
    private String userName;
    /**
     * [密码] 加密存储
     */
    private String password;
    /**
     * [手机号]
     */
    private String phone;
    /**
     * [身份证号] 脱敏存储
     */
    private String idCard;
    /**
     * [性别] 如:男/女/未知
     */
    private String gender;
    /**
     * [用户类型] 如:个人/企业/政府/运维/管理员
     */
    private String userType;
    /**
     * [认证状态] 如:未认证/已认证/认证中/已驳回
     */
    private String certStatus;
    /**
     * [钱包ID] 关联park_wallet.id，可为NULL
     */
    private Long walletId;
    /**
     * [账号状态] 如:正常/禁用/冻结
     */
    private String accountStatus;
    /**
     * [注册时间]
     */
    private LocalDateTime registerTime;
    /**
     * [最后登录时间]
     */
    private LocalDateTime lastLoginTime;
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

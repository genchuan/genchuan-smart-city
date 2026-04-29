package cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberuser;

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
 * 会员用户 DO
 *
 * @author 亘川智城
 */
@TableName("member_user")
@KeySequence("member_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberUserDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态：0-禁用，1-正常
     */
    private Integer status;
    /**
     * 注册IP
     */
    private String registerIp;
    /**
     * 注册终端
     */
    private Integer registerTerminal;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 性别：0-未知，1-男，2-女
     */
    private Integer sex;
    /**
     * 所在地区ID
     */
    private Long areaId;
    /**
     * 出生日期
     */
    private LocalDateTime birthday;
    /**
     * 会员备注
     */
    private String mark;
    /**
     * 积分
     */
    private Integer point;
    /**
     * 用户标签编号列表，逗号分隔
     */
    private String tagIds;
    /**
     * 会员等级ID
     */
    private Long levelId;
    /**
     * 经验值
     */
    private Integer experience;
    /**
     * 用户分组ID
     */
    private Long groupId;
    /**
     * 会员到期时间
     */
    private LocalDateTime expireTime;
    /**
     * 自动续费：0-关闭，1-开启
     */
    private Integer autoRenew;


}
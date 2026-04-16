package cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo;

import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用户信息 DO
 *
 * @author 亘川智城
 */
@TableName("user_info")
@KeySequence("user_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 用户编号
     */
    private String userNo;
    /**
     * 用户姓名
     */
    private String nickname;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 用户类型：个人/企业
     */
    private String userType;
    /**
     * 状态：正常/禁用
     */
    private String status;
    /**
     * 注册时间
     */
    private LocalDateTime registerTime;
    /**
     * 最后登录时间
     */
    private LocalDateTime loginTime;
    /**
     * 钱包余额
     */
    private BigDecimal walletBalance;
    /**
     * 绑定车辆数
     */
    private Integer carCount;
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
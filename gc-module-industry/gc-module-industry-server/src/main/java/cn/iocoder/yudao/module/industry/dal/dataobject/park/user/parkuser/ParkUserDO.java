package cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkuser;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车系统用户 DO
 *
 * @author lxs
 */
@TableName("park_user")
@KeySequence("park_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkUserDO extends BaseDO {

    /**
     * 主键ID[系统用户唯一标识]
     */
    @TableId
    private Long id;
    /**
     * 登录账号[用户登录系统使用的账号名称]
     */
    private String userAccount;
    /**
     * 手机号[加密手机号，用于用户联系与登录校验]
     */
    private String userPhone;
    /**
     * 用户类型[个人/企业/政府/运维人员/商户管理员]
     */
    private String userType;
    /**
     * 身份证号[加密身份证号，仅个人用户使用]
     */
    private String idCard;
    /**
     * 企业名称[仅企业用户使用]
     */
    private String enterpriseName;
    /**
     * 统一社会信用代码[仅企业用户使用]
     */
    private String enterpriseCode;
    /**
     * 政府部门[政府部门名称，仅政府用户使用]
     */
    private String govDepartment;
    /**
     * 认证状态[未认证/待审核/已认证/认证失败]
     */
    private String certStatus;
    /**
     * 钱包余额[用户钱包可用余额]
     */
    private BigDecimal walletBalance;
    /**
     * 冻结余额[当前被冻结不可用的余额]
     */
    private BigDecimal freezeBalance;
    /**
     * 状态[正常/禁用]
     */
    private String status;
    /**
     * 通用扩展字段1[预留扩展字段]
     */
    private String extCommon1;
    /**
     * 通用扩展字段2[预留扩展字段]
     */
    private String extCommon2;
    /**
     * 通用扩展字段3[预留扩展字段]
     */
    private String extCommon3;
    /**
     * 通用扩展字段4[预留扩展字段]
     */
    private String extCommon4;
    /**
     * 备注[用户相关备注说明]
     */
    private String remark;

}

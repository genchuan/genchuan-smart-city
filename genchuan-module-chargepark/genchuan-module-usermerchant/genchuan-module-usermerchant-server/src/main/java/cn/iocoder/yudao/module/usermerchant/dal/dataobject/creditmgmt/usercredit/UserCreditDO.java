package cn.iocoder.yudao.module.usermerchant.dal.dataobject.creditmgmt.usercredit;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 用户信用 DO
 *
 * @author 亘川智城
 */
@TableName("user_credit")
@KeySequence("user_credit_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreditDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名称
     */
    @TableField(exist = false)
    private String nickname;
    /**
     * 信用分，默认100
     */
    private Integer creditScore;
    /**
     * 信用等级：优秀/良好/中等/较差/极差
     */
    private String creditLevel;
    /**
     * 评分规则编码
     */
    private String ruleCode;
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
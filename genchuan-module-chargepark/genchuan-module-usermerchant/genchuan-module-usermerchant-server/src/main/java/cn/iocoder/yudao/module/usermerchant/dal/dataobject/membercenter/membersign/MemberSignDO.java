package cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membersign;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会员签到 DO
 *
 * @author 亘川智城
 */
@TableName("member_sign")
@KeySequence("member_sign_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignDO extends BaseDO {

    /**
     * 签到记录ID
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 签到日期
     */
    private LocalDate signDate;
    /**
     * 连续签到天数
     */
    private Integer continuousDays;
    /**
     * 本次签到获得积分
     */
    private Integer point;
    /**
     * 本次签到获得经验
     */
    private Integer experience;
    /**
     * 记录状态：1-正常，0-异常
     */
    private Integer status;


}
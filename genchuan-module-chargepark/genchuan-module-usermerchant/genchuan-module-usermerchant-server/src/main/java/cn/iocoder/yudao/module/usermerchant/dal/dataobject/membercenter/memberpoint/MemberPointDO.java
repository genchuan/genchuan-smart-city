package cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberpoint;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会员积分 DO
 *
 * @author 亘川智城
 */
@TableName("member_point")
@KeySequence("member_point_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberPointDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 变动积分
     */
    private Integer changeAmount;
    /**
     * 变动后的总积分
     */
    private Integer totalPoint;
    /**
     * 变动类型：1-获取，2-消耗
     */
    private Integer changeType;
    /**
     * 变动原因
     */
    private String changeReason;
    /**
     * 记录状态：0-异常，1-正常
     */
    private Integer status;
    /**
     * 核查结果
     */
    private String checkResult;
    /**
     * 核查时间
     */
    private LocalDateTime checkTime;
    /**
     * 核查人
     */
    private String checkBy;
    /**
     * 业务编码
     */
    private String bizId;
    /**
     * 业务类型
     */
    private Integer bizType;
    /**
     * 积分标题
     */
    private String title;
    /**
     * 积分描述
     */
    private String description;


}
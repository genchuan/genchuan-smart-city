package cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会员等级 DO
 *
 * @author 亘川智城
 */
@TableName("member_level")
@KeySequence("member_level_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLevelDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 等级名称
     */
    private String name;
    /**
     * 等级数值（排序用）
     */
    private Integer levelValue;
    /**
     * 升级条件（JSON）
     */
    private String upgradeCondition;
    /**
     * 权益内容（JSON）
     */
    private String benefits;
    /**
     * 状态：0-未生效，1-已生效
     */
    private Integer status;
    /**
     * 生效时间
     */
    private LocalDateTime effectiveTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 该等级下的会员用户数量
     */
    private Integer memberCount;


}
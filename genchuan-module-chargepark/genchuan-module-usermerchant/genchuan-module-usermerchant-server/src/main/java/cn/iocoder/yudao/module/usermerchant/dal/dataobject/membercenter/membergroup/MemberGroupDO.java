package cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membergroup;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会员分组 DO
 *
 * @author 亘川智城
 */
@TableName("member_group")
@KeySequence("member_group_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberGroupDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 分组名称
     */
    private String name;
    /**
     * 分组描述
     */
    private String description;
    /**
     * 分组规则（JSON）
     */
    private String rule;
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


}
package cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会员配置 DO
 *
 * @author 亘川智城
 */
@TableName("member_config")
@KeySequence("member_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberConfigDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 配置类型
     */
    private String configType;
    /**
     * 权益内容（JSON或文本）
     */
    private String content;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态：0-未生效，1-已生效
     */
    private Integer status;
    /**
     * 生效时间
     */
    private LocalDateTime effectiveTime;


}
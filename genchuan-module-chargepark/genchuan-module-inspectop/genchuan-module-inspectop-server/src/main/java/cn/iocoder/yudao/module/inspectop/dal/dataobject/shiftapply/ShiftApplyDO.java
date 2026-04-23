package cn.iocoder.yudao.module.inspectop.dal.dataobject.shiftapply;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 换班申请 DO
 *
 * @author zhucongquan
 */
@TableName("shift_apply")
@KeySequence("shift_apply_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftApplyDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 申请人ID
     */
    private Long applyUserId;
    /**
     * 换班对象ID
     */
    private Long targetUserId;
    /**
     * 原日期
     */
    private LocalDateTime oldDate;
    /**
     * 新日期
     */
    private LocalDateTime newDate;
    /**
     * 申请状态
     */
    private String status;
    /**
     * 审核人ID
     */
    private Long auditUserId;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 生效时间
     */
    private LocalDateTime effectTime;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
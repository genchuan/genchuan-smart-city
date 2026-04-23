package cn.iocoder.yudao.module.inspectop.dal.dataobject.handoverlog;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 交接日志 DO
 *
 * @author zhucongquan
 */
@TableName("handover_log")
@KeySequence("handover_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HandoverLogDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 交接人员ID
     */
    private Long userId;
    /**
     * 交接日期
     */
    private LocalDateTime handoverDate;
    /**
     * 交接内容
     */
    private String content;
    /**
     * 日志状态
     */
    private String status;
    /**
     * 确认人ID
     */
    private Long confirmUserId;
    /**
     * 确认时间
     */
    private LocalDateTime confirmTime;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
package cn.iocoder.yudao.module.inspectop.dal.dataobject.scheduleview;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 排班查看 DO
 *
 * @author zhucongquan
 */
@TableName("schedule_view")
@KeySequence("schedule_view_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleViewDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 巡检人员ID
     */
    private Long userId;
    /**
     * 排班日期
     */
    private LocalDateTime scheduleDate;
    /**
     * 班次类型
     */
    private String shiftType;
    /**
     * 排班状态
     */
    private String status;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
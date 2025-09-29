package cn.iocoder.yudao.module.datacenter.dal.dataobject.staffalert;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 人员异常报警 DO
 *
 * @author zcq
 */
@TableName("gc_staff_alert")
@KeySequence("gc_staff_alert_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffAlertDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 报警ID
     */
    private String alertId;
    /**
     * 人员ID
     */
    private String staffId;
    /**
     * 人员姓名
     */
    private String staffName;
    /**
     * 报警类型
     */
    private String alertType;
    /**
     * 报警描述
     */
    private String alertDescription;
    /**
     * 报警时间
     */
    private LocalDateTime alertTime;
    /**
     * 报警级别
     */
    private String alertLevel;
    /**
     * 处理状态
     */
    private String processStatus;
    /**
     * 处理人员ID
     */
    private String processorId;
    /**
     * 处理人员姓名
     */
    private String processorName;
    /**
     * 处理措施
     */
    private String processMeasure;
    /**
     * 处理时间
     */
    private LocalDateTime processTime;
    /**
     * 处理结果
     */
    private String processResult;

}
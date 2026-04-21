package cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectreport;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡检上报 DO
 *
 * @author zhucongquan
 */
@TableName("inspect_report")
@KeySequence("inspect_report_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectReportDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联任务ID
     */
    private Long taskId;
    /**
     * 问题类型
     */
    private String type;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 上报状态
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
     * 处置人ID
     */
    private Long processUserId;
    /**
     * 处置时间
     */
    private LocalDateTime processTime;
    /**
     * 上报内容
     */
    private String content;
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
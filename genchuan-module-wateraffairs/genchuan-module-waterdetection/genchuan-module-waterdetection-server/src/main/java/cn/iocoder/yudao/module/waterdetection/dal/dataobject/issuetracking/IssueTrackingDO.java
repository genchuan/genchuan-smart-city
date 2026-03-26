package cn.iocoder.yudao.module.waterdetection.dal.dataobject.issuetracking;

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
 * 问题上报与闭环跟踪 DO
 *
 * @author zcq
 */
@TableName("gc_issue_tracking")
@KeySequence("gc_issue_tracking_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueTrackingDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 问题ID
     */
    private String issueId;
    /**
     * 问题类型(漏点/设备故障/标识牌损坏)
     */
    private String issueType;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 派单时间
     */
    private LocalDateTime dispatchTime;
    /**
     * 维修人员ID
     */
    private String repairStaffId;
    /**
     * 修复时间
     */
    private LocalDateTime repairTime;
    /**
     * 验收结果
     */
    private String inspectionResult;
    /**
     * 闭环状态
     */
    private String closureStatus;

}
package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifytrack;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检整改进度跟踪视图 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_rectify_track")
@KeySequence("biz_inspect_rectify_track_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectRectifyTrackDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 整改任务ID
     */
    private String rectifyTaskId;
    /**
     * 整改任务编码
     */
    private String rectifyTaskCode;
    /**
     * 关联问题名称
     */
    private String problemName;
    /**
     * 责任部门名称
     */
    private String deptName;
    /**
     * 整改负责人姓名
     */
    private String leaderUserName;
    /**
     * 整改任务状态
     */
    private String rectifyStatus;
    /**
     * 整改时限（小时）
     */
    private Integer rectifyDeadlineHour;
    /**
     * 整改截止时间
     */
    private LocalDateTime rectifyDeadlineTime;
    /**
     * 剩余时间（分钟）
     */
    private Integer remainTimeMin;
    /**
     * 是否超期
     */
    private Boolean isOverdue;
    /**
     * 最新进展时间
     */
    private LocalDateTime latestProgressTime;
    /**
     * 最新进展描述
     */
    private String latestProgressDesc;
    /**
     * 最新进展照片URL
     */
    private String latestProgressPhoto;
    /**
     * 催办次数
     */
    private Integer remindCount;
    /**
     * 最后催办时间
     */
    private LocalDateTime lastRemindTime;
    /**
     * 派发时间
     */
    private LocalDateTime dispatchTime;

}
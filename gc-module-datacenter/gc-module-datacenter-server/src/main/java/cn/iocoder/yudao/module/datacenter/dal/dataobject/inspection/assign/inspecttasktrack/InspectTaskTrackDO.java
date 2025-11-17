package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.assign.inspecttasktrack;

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
 * 巡查巡检任务跟踪 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_task_track")
@KeySequence("biz_inspect_task_track_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectTaskTrackDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 任务ID
     */
    private String taskId;
    /**
     * 任务编码
     */
    private String taskCode;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 负责人员ID
     */
    private String personId;
    /**
     * 负责人员姓名
     */
    private String personName;
    /**
     * 所属区域代码
     */
    private String areaCode;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 任务状态
     */
    private String taskStatus;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 完成时间
     */
    private LocalDateTime completeTime;
    /**
     * 截止时间
     */
    private LocalDateTime deadlineTime;
    /**
     * 检查项总数
     */
    private Integer totalCkItem;
    /**
     * 已完成检查项
     */
    private Integer completedCkItem;
    /**
     * 上传照片数
     */
    private Integer uploadPhotoCount;
    /**
     * 是否超时
     */
    private Integer isOvertime;
    /**
     * 催办次数
     */
    private Integer remindCount;
    /**
     * 最后催办时间
     */
    private LocalDateTime lastRemindTime;

}
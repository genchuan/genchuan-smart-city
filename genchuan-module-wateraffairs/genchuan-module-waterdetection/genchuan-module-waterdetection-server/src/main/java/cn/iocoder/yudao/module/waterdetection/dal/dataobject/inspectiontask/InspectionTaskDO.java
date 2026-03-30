package cn.iocoder.yudao.module.waterdetection.dal.dataobject.inspectiontask;

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
 * 巡检任务派发与执行 DO
 *
 * @author zcq
 */
@TableName("gc_inspection_task")
@KeySequence("gc_inspection_task_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionTaskDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 任务ID
     */
    private String taskId;
    /**
     * 巡检人员ID
     */
    private String inspectorId;
    /**
     * 任务内容
     */
    private String taskContent;
    /**
     * 派发时间
     */
    private LocalDateTime dispatchTime;
    /**
     * 接收时间
     */
    private LocalDateTime receiveTime;
    /**
     * 签到时间
     */
    private LocalDateTime checkinTime;
    /**
     * 检查项结果(正常/异常)
     */
    private String inspectionResult;
    /**
     * 现场照片URL
     */
    private String photoUrl;
    /**
     * 定位信息
     */
    private String locationInfo;

}
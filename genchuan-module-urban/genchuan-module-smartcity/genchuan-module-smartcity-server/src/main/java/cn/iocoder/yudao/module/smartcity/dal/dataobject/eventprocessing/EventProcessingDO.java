package cn.iocoder.yudao.module.smartcity.dal.dataobject.eventprocessing;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件处理 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_event_processing")
@KeySequence("smartcity_event_processing_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventProcessingDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 事件处理编号
     */
    private String eventHandlingNumber;
    /**
     * 关联事件上报编号
     */
    private String relatedEventReportingId;
    /**
     * 处理部门
     */
    private String processingDepartment;
    /**
     * 处理人员
     */
    private String processingPersonnel;
    /**
     * 接收时间
     */
    private String receptionTime;
    /**
     * 处理开始时间
     */
    private LocalDateTime processingStartTime;
    /**
     * 处理结束时间
     */
    private String endTimeOfProcessing;
    /**
     * 处理时长
     */
    private String processingTime;
    /**
     * 处理措施
     */
    private String handlingMeasures;
    /**
     * 使用资源
     */
    private String utilizeResources;
    /**
     * 处理过程描述
     */
    private String processDescription;
    /**
     * 处理结果
     */
    private String processingResults;
    /**
     * 是否解决
     */
    private String hasItBeenResolved;
    /**
     * 是否需二次处理
     */
    private String isSecondaryProcessing;
    /**
     * 处理反馈
     */
    private String handlingFeedback;
    /**
     * 验收人
     */
    private String acceptancePersonnel;
    /**
     * 验收时间
     */
    private LocalDateTime acceptanceTime;
    /**
     * 验收结果
     */
    private String acceptanceResults;
    /**
     * 评价等级
     */
    private String evaluationLevel;
    /**
     * 评价意见 
     */
    private String evaluationOpinion;

}
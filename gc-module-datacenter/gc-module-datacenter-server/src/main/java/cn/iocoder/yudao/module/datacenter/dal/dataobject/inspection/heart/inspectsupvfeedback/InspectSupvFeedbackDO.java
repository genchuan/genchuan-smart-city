package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvfeedback;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检督办结果反馈 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_supv_feedback")
@KeySequence("biz_inspect_supv_feedback_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectSupvFeedbackDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 反馈ID
     */
    private String feedbackId;
    /**
     * 反馈编码
     */
    private String feedbackCode;
    /**
     * 关联督办任务ID
     */
    private String supvTaskId;
    /**
     * 关联督办任务编码
     */
    private String supvTaskCode;
    /**
     * 关联问题名称
     */
    private String problemName;
    /**
     * 督办结果
     */
    private String supvResult;
    /**
     * 督办结果说明
     */
    private String supvResultDesc;
    /**
     * 督办反馈意见
     */
    private String supvFeedbackOpinion;
    /**
     * 关联整改验收结果
     */
    private String rectifyAcceptResult;
    /**
     * 反馈人
     */
    private String feedbackUser;
    /**
     * 反馈时间
     */
    private LocalDateTime feedbackTime;
    /**
     * 督办任务状态变更
     */
    private String supvStatusChange;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}
package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresultfeedback;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检结果反馈 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_result_feedback")
@KeySequence("biz_inspect_result_feedback_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectResultFeedbackDO extends BaseDO {

    /**
     * 主键ID
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
     * 关联公示ID
     */
    private String publicId;
    /**
     * 关联公示标题
     */
    private String publicTitle;
    /**
     * 反馈人姓名
     */
    private String feedbackUserName;
    /**
     * 反馈人手机号
     */
    private String feedbackUserPhone;
    /**
     * 反馈内容
     */
    private String feedbackContent;
    /**
     * 反馈照片URLs
     */
    private String feedbackPhotoUrls;
    /**
     * 反馈时间
     */
    private LocalDateTime feedbackTime;
    /**
     * 反馈状态
     */
    private String feedbackStatus;
    /**
     * 核实人员ID
     */
    private String verifyUserId;
    /**
     * 核实人员姓名
     */
    private String verifyUserName;
    /**
     * 核实结果
     */
    private String verifyResult;
    /**
     * 核实意见
     */
    private String verifyOpinion;
    /**
     * 关联问题ID
     */
    private String problemId;
    /**
     * 处理结果
     */
    private String handleResult;
    /**
     * 处理人
     */
    private String handleUser;
    /**
     * 处理时间
     */
    private LocalDateTime handleTime;
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
package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalquestionnaire;

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
 * 问卷调查管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_questionnaire")
@KeySequence("sys_eval_questionnaire_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalQuestionnaireDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 问卷ID
     */
    private String questionnaireId;
    /**
     * 问卷名称
     */
    private String questionnaireName;
    /**
     * 问卷编码
     */
    private String questionnaireCode;
    /**
     * 关联任务ID
     */
    private String evalTaskId;
    /**
     * 关联任务名称
     */
    private String evalTaskName;
    /**
     * 关联指标项ID
     */
    private String idxItemId;
    /**
     * 关联指标项名称
     */
    private String idxItemName;
    /**
     * 调查对象范围
     */
    private String targetScope;
    /**
     * 发放方式
     */
    private String releaseMethod;
    /**
     * 问卷链接
     */
    private String questionnaireUrl;
    /**
     * 问卷二维码
     */
    private String questionnaireQrcode;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 问卷状态
     */
    private String questionnaireStatus;
    /**
     * 填写人数
     */
    private Integer fillCount;
    /**
     * 问卷内容
     */
    private String questionnaireContent;
    /**
     * 创建人(业务)
     */
    private String createUserBiz;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
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
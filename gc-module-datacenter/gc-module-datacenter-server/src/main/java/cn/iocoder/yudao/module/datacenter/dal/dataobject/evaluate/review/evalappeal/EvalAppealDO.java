package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalappeal;

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
 * 申诉管理 DO
 *
 * @author zhucongquan
 */
@TableName("sys_eval_appeal")
@KeySequence("sys_eval_appeal_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalAppealDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 申诉ID
     */
    private String appealId;
    /**
     * 申诉编号
     */
    private String appealCode;
    /**
     * 关联公示ID
     */
    private String resultPublicId;
    /**
     * 关联任务ID
     */
    private String evalTaskId;
    /**
     * 评价对象ID
     */
    private String evalObjectId;
    /**
     * 评价对象名称
     */
    private String evalObjectName;
    /**
     * 申诉人ID
     */
    private String appealUserId;
    /**
     * 申诉人姓名
     */
    private String appealUserName;
    /**
     * 申诉理由
     */
    private String appealReason;
    /**
     * 证明材料
     */
    private String proofMats;
    /**
     * 申诉状态
     */
    private String appealStatus;
    /**
     * 提交时间
     */
    private LocalDateTime submitTime;
    /**
     * 撤销时间
     */
    private LocalDateTime cancelTime;
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
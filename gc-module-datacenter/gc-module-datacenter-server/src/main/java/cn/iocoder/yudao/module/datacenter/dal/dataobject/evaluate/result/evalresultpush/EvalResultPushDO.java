package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultpush;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 结果关联推送 DO
 *
 * @author zcq
 */
@TableName("sys_eval_result_push")
@KeySequence("sys_eval_result_push_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalResultPushDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 推送ID
     */
    private String resultPushId;
    /**
     * 推送编号
     */
    private String resultPushCode;
    /**
     * 关联存档ID
     */
    private String resultArchId;
    /**
     * 评价对象ID
     */
    private String evalObjectId;
    /**
     * 评价对象名称
     */
    private String evalObjectName;
    /**
     * 推送目标
     */
    private String pushTarget;
    /**
     * 推送方式
     */
    private String pushMethod;
    /**
     * 推送地址
     */
    private String pushAddress;
    /**
     * 推送内容
     */
    private String pushContent;
    /**
     * 推送状态
     */
    private String pushStatus;
    /**
     * 推送时间
     */
    private LocalDateTime pushTime;
    /**
     * 失败原因
     */
    private String failReason;
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
package cn.iocoder.yudao.module.evaluate.dal.dataobject.record;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 考察记录 DO
 *
 * @author 亘川智城
 */
@TableName("inspect_record")
@KeySequence("inspect_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 考察记录UUID
     */
    private String recordId;
    /**
     * 记录编号
     */
    private String code;
    /**
     * 关联考察计划ID
     */
    private String planId;
    /**
     * 考察对象ID
     */
    private String objectId;
    /**
     * 考察人员ID（多个用逗号分隔）
     */
    private String inspectBy;
    /**
     * 考察时间
     */
    private LocalDateTime inspectTime;
    /**
     * 考察得分
     */
    private BigDecimal totalScore;
    /**
     * 最终考察得分
     */
    private BigDecimal finalScore;
    /**
     * 问题描述摘要
     */
    private String problemDesc;
    /**
     * 照片数量
     */
    private Integer photoCount;
    /**
     * 状态
     */
    private String status;
    /**
     * 提交时间
     */
    private LocalDateTime submitTime;
    /**
     * 审核人
     */
    private String auditBy;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 驳回意见摘要
     */
    private String rejectOpinion;
    /**
     * 草稿保存时间
     */
    private LocalDateTime draftTime;
    /**
     * 最后编辑时间
     */
    private LocalDateTime lastEditTime;
    /**
     * 最后编辑人
     */
    private String lastEditBy;
    /**
     * 照片上传状态：未上传/部分上传/全部上传
     */
    private String photoStatus;
    /**
     * 撤回次数
     */
    private Integer recallCount;
    /**
     * 最后撤回时间
     */
    private LocalDateTime lastRecallTime;
    /**
     * 待审核时长（小时）
     */
    private BigDecimal waitAuditHour;
    /**
     * 数据同步状态：已同步/同步中/未同步
     */
    private String dataSyncStatus;
    /**
     * 同步时间
     */
    private LocalDateTime syncTime;
    /**
     * 重新提交次数
     */
    private Integer resubmitCount;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}
package cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagepermitapply;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 排水许可证申请 DO
 *
 * @author 超级管理员
 */
@TableName("smartcity_drainage_permit_apply")
@KeySequence("smartcity_drainage_permit_apply_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrainagePermitApplyDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 申请编号
     */
    private String applyNo;
    /**
     * 排水户名称
     */
    private String userName;
    /**
     * 排水水质检测报告文件
     */
    private String waterQualityReport;
    /**
     * 日均排水量（吨）
     */
    private Double dailyDrainage;
    /**
     * 重点排污单位证明文件路径
     */
    private String pollutionProof;
    /**
     * 历史违规记录
     */
    private String violationHistory;
    /**
     * 申请状态
     *
     * 枚举 {@link TODO crm_audit_status 对应的类}
     */
    private String applyStatus;
    /**
     * 审核人
     */
    private String approver;
    /**
     * 审核时间
     */
    private LocalDateTime approveTime;
    /**
     * 审核意见
     */
    private String approveComment;

}
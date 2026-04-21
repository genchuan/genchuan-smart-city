package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.targetmgmt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 指标管理 DO
 *
 * @author 芋道源码
 */
@TableName("target_mgmt")
@KeySequence("target_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TargetMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 指标名称
     */
    private String targetName;
    /**
     * 指标总分
     */
    private BigDecimal totalScore;
    /**
     * 预警阈值
     */
    private BigDecimal warnThreshold;
    /**
     * 评价人类型：教职工/家长/领导
     */
    private String evaluatorType;
    /**
     * 计分方式：累计赋分/接口赋分
     */
    private String scoreType;
    /**
     * 启用时间
     */
    private LocalDateTime enableTime;
    /**
     * 停用时间
     */
    private LocalDateTime disableTime;
    /**
     * 状态：未启用/已启用
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;


}
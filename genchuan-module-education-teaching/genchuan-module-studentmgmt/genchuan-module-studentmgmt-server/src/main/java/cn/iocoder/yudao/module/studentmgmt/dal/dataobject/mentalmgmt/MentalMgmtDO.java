package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.mentalmgmt;

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
 * 心理管理 DO
 *
 * @author 芋道源码
 */
@TableName("mental_mgmt")
@KeySequence("mental_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentalMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 学生 ID
     */
    private Long studentId;
    /**
     * 心理状态：正常/关注/高危
     */
    private String mentalStatus;
    /**
     * 风险等级：低/中/高
     */
    private String riskLevel;
    /**
     * 评估时间
     */
    private LocalDateTime evaluateTime;
    /**
     * 咨询预约时间
     */
    private LocalDateTime consultTime;
    /**
     * 干预时间
     */
    private LocalDateTime interveneTime;
    /**
     * 干预内容
     */
    private String interveneContent;
    /**
     * 状态：待评估/咨询中/已干预
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
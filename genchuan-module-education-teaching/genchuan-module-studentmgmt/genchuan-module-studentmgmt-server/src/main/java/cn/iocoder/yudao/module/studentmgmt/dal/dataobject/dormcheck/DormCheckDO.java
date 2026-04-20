package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcheck;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 宿舍考勤 DO
 *
 * @author 芋道源码
 */
@TableName("dorm_check")
@KeySequence("dorm_check_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DormCheckDO extends BaseDO {

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
     * 考勤时间
     */
    private LocalDateTime checkTime;
    /**
     * 考勤状态：正常/迟到/未到
     */
    private String checkStatus;
    /**
     * 异常类型：无/晚归/未归
     */
    private String abnormalType;
    /**
     * 补卡时间
     */
    private LocalDateTime repairTime;
    /**
     * 补卡人
     */
    private String repairUser;
    /**
     * 推送时间
     */
    private LocalDateTime pushTime;
    /**
     * 在寝率
     */
    private BigDecimal inRate;
    /**
     * 状态：正常/异常
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
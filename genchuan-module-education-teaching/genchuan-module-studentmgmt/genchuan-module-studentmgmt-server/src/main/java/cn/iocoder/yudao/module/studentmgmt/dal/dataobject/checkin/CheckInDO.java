package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.checkin;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 报到管理 DO
 *
 * @author 芋道源码
 */
@TableName("check_in")
@KeySequence("check_in_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckInDO extends BaseDO {

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
     * 中考成绩
     */
    private BigDecimal examScore;
    /**
     * 补充信息
     */
    private String supplyInfo;
    /**
     * 报到确认时间
     */
    private LocalDateTime confirmTime;
    /**
     * 审核人
     */
    private String auditUser;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 账号创建时间
     */
    private LocalDateTime accountCreateTime;
    /**
     * 账号状态：未创建/已创建
     */
    private String accountStatus;
    /**
     * 状态：待确认/待审核/已报到
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

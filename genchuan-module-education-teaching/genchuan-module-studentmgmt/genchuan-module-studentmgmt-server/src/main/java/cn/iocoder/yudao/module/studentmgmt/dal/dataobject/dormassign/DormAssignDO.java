package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormassign;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 宿舍分配 DO
 *
 * @author 芋道源码
 */
@TableName("dorm_assign")
@KeySequence("dorm_assign_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DormAssignDO extends BaseDO {

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
     * 宿舍号
     */
    private String dormNum;
    /**
     * 床位 ID
     */
    private Long bedId;
    /**
     * 分配规则
     */
    private String ruleContent;
    /**
     * 分配时间
     */
    private LocalDateTime assignTime;
    /**
     * 调整时间
     */
    private LocalDateTime adjustTime;
    /**
     * 分配完成率
     */
    private BigDecimal finishRate;
    /**
     * 状态：未分配/已分配
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
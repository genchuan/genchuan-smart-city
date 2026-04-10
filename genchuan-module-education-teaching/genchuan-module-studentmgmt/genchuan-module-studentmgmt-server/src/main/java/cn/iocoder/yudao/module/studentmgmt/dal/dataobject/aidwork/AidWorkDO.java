package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.aidwork;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 奖助勤贷 DO
 *
 * @author 芋道源码
 */
@TableName("aid_work")
@KeySequence("aid_work_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AidWorkDO extends BaseDO {

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
     * 资助类型：奖学金/助学金/助学贷款/勤工俭学
     */
    private String aidType;
    /**
     * 申请金额
     */
    private BigDecimal applyAmount;
    /**
     * 申报时间
     */
    private LocalDateTime applyTime;
    /**
     * 审核人
     */
    private String auditUser;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 流程状态：跟进中/已完成
     */
    private String processStatus;
    /**
     * 状态：待审核/已通过/已完成
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
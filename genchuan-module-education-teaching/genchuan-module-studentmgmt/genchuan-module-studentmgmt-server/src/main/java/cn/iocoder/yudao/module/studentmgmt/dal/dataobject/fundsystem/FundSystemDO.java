package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.fundsystem;

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
 * 资助系统 DO
 *
 * @author 芋道源码
 */
@TableName("fund_system")
@KeySequence("fund_system_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundSystemDO extends BaseDO {

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
     * 资助类型：助学金/勤工俭学/其他
     */
    private String fundType;
    /**
     * 申请金额
     */
    private BigDecimal applyAmount;
    /**
     * 申请时间
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
     * 状态：待审核/已汇总
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
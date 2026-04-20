package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.staymgmt;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 留宿管理 DO
 *
 * @author 芋道源码
 */
@TableName("stay_mgmt")
@KeySequence("stay_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StayMgmtDO extends BaseDO {

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
     * 留宿日期
     */
    private LocalDate stayDate;
    /**
     * 留宿原因
     */
    private String stayReason;
    /**
     * 申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 家长确认时间
     */
    private LocalDateTime parentConfirmTime;
    /**
     * 审核人
     */
    private String auditUser;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 状态：待确认/待审核/已通过
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

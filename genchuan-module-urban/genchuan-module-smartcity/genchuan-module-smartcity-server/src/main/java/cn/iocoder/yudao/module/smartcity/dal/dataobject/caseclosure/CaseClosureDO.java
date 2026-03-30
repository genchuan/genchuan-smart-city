package cn.iocoder.yudao.module.smartcity.dal.dataobject.caseclosure;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 案件结案 DO
 *
 * @author 超级管理员
 */
@TableName("smartcity_case_closure")
@KeySequence("smartcity_case_closure_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseClosureDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 案件ID
     */
    private String caseId;
    /**
     * 结案原因
     */
    private String closureReason;
    /**
     * 结案部门
     */
    private String closureDepartment;
    /**
     * 结案人
     */
    private String closurePerson;
    /**
     * 结案时间
     */
    private LocalDateTime closureTime;
    /**
     * 审批人
     */
    private String approvalPerson;
    /**
     * 审批时间
     */
    private LocalDateTime approvalTime;
    /**
     * 审批意见
     */
    private String approvalOpinion;
    /**
     * 归档编号
     */
    private String archiveNumber;
    /**
     * 归档位置
     */
    private String archiveLocation;

}
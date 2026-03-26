package cn.iocoder.yudao.module.smartcity.dal.dataobject.casedisposal;

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
 * 案件处理 DO
 *
 * @author 朱聪权
 */
@TableName("smartcity_case_disposal")
@KeySequence("smartcity_case_disposal_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseDisposalDO extends BaseDO {

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
     * 处理类型
     */
    private String disposalType;
    /**
     * 处理部门
     */
    private String disposalDepartment;
    /**
     * 处理人
     */
    private String disposalPerson;
    /**
     * 处理开始时间
     */
    private LocalDateTime disposalStartTime;
    /**
     * 处理结束时间
     */
    private LocalDateTime disposalEndTime;
    /**
     * 处理依据
     */
    private String disposalBasis;
    /**
     * 处理内容
     */
    private String disposalContent;
    /**
     * 处理结果
     */
    private String disposalResult;
    /**
     * 处罚金额
     */
    private BigDecimal penaltyAmount;
    /**
     * 处罚类型
     */
    private String penaltyType;

}
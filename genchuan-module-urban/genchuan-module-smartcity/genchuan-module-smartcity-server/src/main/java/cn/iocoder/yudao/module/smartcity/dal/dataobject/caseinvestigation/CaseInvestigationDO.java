package cn.iocoder.yudao.module.smartcity.dal.dataobject.caseinvestigation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 案件调查 DO
 *
 * @author 朱聪权
 */
@TableName("smartcity_case_investigation")
@KeySequence("smartcity_case_investigation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseInvestigationDO extends BaseDO {

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
     * 调查负责人
     */
    private String investigationLeader;
    /**
     * 调查组成员
     */
    private String investigationTeam;
    /**
     * 调查开始时间
     */
    private LocalDateTime investigationStartTime;
    /**
     * 调查结束时间
     */
    private LocalDateTime investigationEndTime;
    /**
     * 调查情况描述
     */
    private String investigationDesc;
    /**
     * 证据情况描述
     */
    private String evidenceDesc;
    /**
     * 证人证言描述
     */
    private String testimonyDesc;
    /**
     * 调查结果
     */
    private String investigationResult;
    /**
     * 处理建议
     */
    private String treatmentSuggestion;

}
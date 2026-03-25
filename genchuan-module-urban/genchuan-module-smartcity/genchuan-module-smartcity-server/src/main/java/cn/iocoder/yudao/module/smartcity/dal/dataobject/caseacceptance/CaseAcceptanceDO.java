package cn.iocoder.yudao.module.smartcity.dal.dataobject.caseacceptance;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 案件受理 DO
 *
 * @author 朱聪权
 */
@TableName("smartcity_case_acceptance")
@KeySequence("smartcity_case_acceptance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseAcceptanceDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 案件编号
     */
    private String caseCode;
    /**
     * 案件名称
     */
    private String caseName;
    /**
     * 案件类型
     */
    private String caseType;
    /**
     * 案件来源
     */
    private String caseSource;
    /**
     * 案件时间
     */
    private LocalDateTime caseTime;
    /**
     * 案件地点
     */
    private String caseLocation;
    /**
     * 报案单位
     */
    private String reportUnit;
    /**
     * 报案人
     */
    private String reportPerson;
    /**
     * 联系电话
     */
    private String reportPhone;
    /**
     * 案件描述
     */
    private String caseDesc;
    /**
     * 案件状态
     */
    private String caseStatus;

}
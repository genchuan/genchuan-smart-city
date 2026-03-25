package cn.iocoder.yudao.module.smartcity.dal.dataobject.oflawenforcementpersonnel;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 执法人员管理 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_of_law_enforcement_personnel")
@KeySequence("smartcity_of_law_enforcement_personnel_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfLawEnforcementPersonnelDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 姓名
     */
    private String fullName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 联系方式
     */
    private String contactInformation;
    /**
     * 所属执法部门
     */
    private String belongingLawDepartment;
    /**
     * 职务
     */
    private String position;
    /**
     * 执法证编号
     */
    private String lawEnforcementNumber;
    /**
     * 执法证有效期
     */
    private LocalDateTime validityCertificate;
    /**
     * 入职时间
     */
    private String entryTime;
    /**
     * 政治面貌
     */
    private String politicalStatus;
    /**
     * 学历
     */
    private String educationalBackground;
    /**
     * 专业
     */
    private String major;
    /**
     * 培训经历
     */
    private String trainingExperience;
    /**
     * 考核成绩
     */
    private String resultOfExamination;
    /**
     * 奖惩记录
     */
    private String rewardsRecord;
    /**
     * 违规违纪情况
     */
    private String violationRegulations;
    /**
     * 负责区域
     */
    private String responsibleArea;
    /**
     * 执法类型
     */
    private String lawEnforcementType;
    /**
     * 紧急联系人姓名
     */
    private String emergencyContactName;
    /**
     * 紧急联系人电话
     */
    private String emergencyContactPhone;

}
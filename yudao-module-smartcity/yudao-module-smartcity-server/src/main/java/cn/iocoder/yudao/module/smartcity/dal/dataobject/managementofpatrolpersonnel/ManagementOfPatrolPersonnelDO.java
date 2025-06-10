package cn.iocoder.yudao.module.smartcity.dal.dataobject.managementofpatrolpersonnel;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡査人员管理 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_management_of_patrol_personnel")
@KeySequence("smartcity_management_of_patrol_personnel_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagementOfPatrolPersonnelDO extends BaseDO {

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
     * 出生日期
     */
    private LocalDateTime dateOfBirth;
    /**
     * 联系方式
     */
    private String contactInformation;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 所属部门
     */
    private String department;
    /**
     * 岗位名称
     */
    private String position;
    /**
     * 入职时间
     */
    private LocalDateTime entryTime;
    /**
     * 巡查区域
     */
    private String patrolArea;
    /**
     * 巡查路线
     */
    private String patrolRoute;
    /**
     * 巡查班次
     */
    private String inspectionShift;
    /**
     * 资质证书编号
     */
    private String qualificationCertificateNumber;
    /**
     * 证书有效期
     */
    private String certificateValidityPeriod;
    /**
     * 培训记录
     */
    private String trainingRecords;
    /**
     * 巡查设备编号
     */
    private String inspectionEquipmentNumber;
    /**
     * 违规记录
     */
    private String violationRecord;
    /**
     * 奖惩情况
     */
    private String rewardAndPunishmentSituation;

}
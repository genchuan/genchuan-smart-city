package cn.iocoder.yudao.module.smartcity.dal.dataobject.personnelinformation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 人员信息 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_personnel_information")
@KeySequence("smartcity_personnel_information_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelInformationDO extends BaseDO {

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
     * 身份证号
     */
    private String idNumber;
    /**
     * 民族
     */
    private String nationality;
    /**
     * 户籍地址
     */
    private String registeredResidenceAddress;
    /**
     * 现居住地址
     */
    private String currentResidentialAddress;
    /**
     * 联系电话
     */
    private String contactNumber;
    /**
     * 电子邮箱
     */
    private String eMail;
    /**
     * 婚姻状况
     */
    private String maritalStatus;
    /**
     * 学历
     */
    private String educationalBackground;
    /**
     * 毕业院校
     */
    private String almaMater;
    /**
     * 专业
     */
    private String major;
    /**
     * 工作单位
     */
    private String workUnit;
    /**
     * 职业
     */
    private String career;
    /**
     * 入职时间
     */
    private LocalDateTime entryTime;
    /**
     * 离职时间（若已离职）
     */
    private LocalDateTime resignationTime;
    /**
     * 紧急联系人姓名
     */
    private String emergencyContactName;
    /**
     * 紧急联系人电话
     */
    private String emergencyContactPhone;
    /**
     * 紧急联系人关系
     */
    private String emergencyContactRelationship;

}
package cn.iocoder.yudao.module.smartcity.dal.dataobject.parking;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车管理用户管理与服务 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_parking")
@KeySequence("smartcity_parking_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String fullName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 电子邮箱
     */
    private String eMail;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 注册时间
     */
    private LocalDateTime registrationTime;
    /**
     * 用户类型
     */
    private String customerType;
    /**
     * 用户等级
     */
    private String userLevel;
    /**
     * 积分
     */
    private String points;
    /**
     * 余额
     */
    private String balance;
    /**
     * 地址
     */
    private String address;
    /**
     * 最近登录时间
     */
    private LocalDateTime recentLoginTime;
    /**
     * 登录 IP
     */
    private String loginIp;
    /**
     * 服务记录 ID
     */
    private String serviceRecordId;
    /**
     * 咨询内容
     */
    private String consultationContent;
    /**
     * 咨询时间
     */
    private LocalDateTime consultationTime;
    /**
     * 反馈内容
     */
    private String feedbackContent;
    /**
     * 反馈时间
     */
    private LocalDateTime feedbackTime;
    /**
     * 投诉内容
     */
    private String complaintContent;
    /**
     * 投诉时间
     */
    private LocalDateTime complaintTime;
    /**
     * 处理状态
     */
    private String processingStatus;
    /**
     * 处理结果
     */
    private String processingResults;
    /**
     * 服务评价
     */
    private String serviceEvaluation;
    /**
     * 评价时间
     */
    private LocalDateTime evaluationTime;

}
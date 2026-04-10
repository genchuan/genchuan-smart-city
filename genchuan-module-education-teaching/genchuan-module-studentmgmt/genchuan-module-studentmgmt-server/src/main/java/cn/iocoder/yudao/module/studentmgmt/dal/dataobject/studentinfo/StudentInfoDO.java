package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo;

import cn.iocoder.yudao.framework.desensitize.core.slider.annotation.MobileDesensitize;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 学生信息 DO
 *
 * @author 芋道源码
 */
@TableName("student_info")
@KeySequence("student_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 学号
     */
    private String studentNo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 学生照片地址
     */
    private String photo;
    /**
     * 学历层次：中专/大专/本科/研究生
     */
    private String educationLevel;
    /**
     * 学习形式：全日制/非全日制/函授
     */
    private String studyForm;
    /**
     * 专业
     */
    private String major;
    /**
     * 年级
     */
    private String grade;
    /**
     * 班级
     */
    private String className;
    /**
     * 学生类型：普通生/特长生/转学生
     */
    private String studentType;
    /**
     * 学籍状态：在籍/休学/退学/异动
     */
    private String status;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 家长联系电话
     */
    private String parentPhone;
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

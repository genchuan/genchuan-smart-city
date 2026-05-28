package cn.iocoder.yudao.module.smartcampus.dal.dataobject.archive;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 学生学籍档案 DO
 *
 * @author 亘川智城
 */
@TableName("student_archive")
@KeySequence("student_archive_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 学生编号
     */
    private String studentNo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 班级ID，关联system_dept.id
     */
    private Long classId;
    /**
     * 专业
     */
    private String major;
    /**
     * 层次
     */
    private String level;
    /**
     * 学习形式
     */
    private String studyType;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 家长电话
     */
    private String parentPhone;
    /**
     * 学籍状态：在籍/休学/退学/异动，关联字典student_archive_status.label
     */
    private String status;
    /**
     * 建档时间
     */
    private LocalDateTime archiveTime;
    /**
     * 流程状态：待审核/正常/已归档，关联字典student_archive_process_status.label
     */
    private String processStatus;
    /**
     * 驳回原因
     */
    private String rejectReason;
    /**
     * 异动原因
     */
    private String changeReason;
    /**
     * 佐证材料
     */
    private String evidenceUrl;
    /**
     * 处分有效期
     */
    private LocalDateTime punishValidTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段，JSON格式（插入时需显式提供值）
     */
    private String extension;


}

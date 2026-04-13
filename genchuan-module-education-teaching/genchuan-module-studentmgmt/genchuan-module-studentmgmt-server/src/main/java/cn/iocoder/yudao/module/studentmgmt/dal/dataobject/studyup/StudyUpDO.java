package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studyup;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 升学管理 DO
 *
 * @author 芋道源码
 */
@TableName("study_up")
@KeySequence("study_up_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyUpDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 学生 ID
     */
    private Long studentId;
    /**
     * 目标院校名称
     */
    private String schoolName;
    /**
     * 院校类型：公办/民办
     */
    private String schoolType;
    /**
     * 意向专业
     */
    private String major;
    /**
     * 升学规划内容
     */
    private String planContent;
    /**
     * 规划时间
     */
    private LocalDateTime planTime;
    /**
     * 跟踪记录时间
     */
    private LocalDateTime recordTime;
    /**
     * 状态：待规划/已规划
     */
    private String status;
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
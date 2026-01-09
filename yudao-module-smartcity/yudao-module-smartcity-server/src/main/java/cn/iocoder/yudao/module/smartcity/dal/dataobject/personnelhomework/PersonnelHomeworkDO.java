package cn.iocoder.yudao.module.smartcity.dal.dataobject.personnelhomework;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 人员作业 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_personnel_homework")
@KeySequence("smartcity_personnel_homework_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelHomeworkDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 作业编号
     */
    private String jobNumber;
    /**
     * 作业人员编号
     */
    private String operatorId;
    /**
     * 作业区域编号
     */
    private String assignmentAreaNumber;
    /**
     * 作业时间
     */
    private LocalDateTime operationTime;
    /**
     * 作业内容
     */
    private String homeworkContent;
    /**
     * 作业方式
     */
    private String operationMode;
    /**
     * 备注
     */
    private String notes;

}
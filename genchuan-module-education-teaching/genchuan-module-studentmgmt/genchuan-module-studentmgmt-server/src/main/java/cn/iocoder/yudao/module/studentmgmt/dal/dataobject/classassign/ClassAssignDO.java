package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.classassign;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 分班管理 DO
 *
 * @author 芋道源码
 */
@TableName("class_assign")
@KeySequence("class_assign_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassAssignDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 分班规则
     */
    private String ruleContent;
    /**
     * 分班学生数
     */
    private Integer studentNum;
    /**
     * 分班时间
     */
    private LocalDateTime assignTime;
    /**
     * 确认人
     */
    private String confirmUser;
    /**
     * 确认时间
     */
    private LocalDateTime confirmTime;
    /**
     * 状态：未分班/已分班
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
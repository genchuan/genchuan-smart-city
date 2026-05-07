package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.parentreply;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 家长回复 DO
 *
 * @author 芋道源码
 */
@TableName("parent_reply")
@KeySequence("parent_reply_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentReplyDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联沟通消息ID
     */
    private Long communicateId;
    /**
     * 学生ID
     */
    private Long studentId;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 家长回复内容
     */
    private String parentReplyContent;
    /**
     * 家长回复时间
     */
    private LocalDateTime parentReplyTime;
    /**
     * 老师回复内容
     */
    private String teacherReplyContent;
    /**
     * 老师回复时间
     */
    private LocalDateTime teacherReplyTime;
    /**
     * 阅读状态：未读/已读
     */
    private String readStatus;
    /**
     * 回复状态：未回复/已回复
     */
    private String replyStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
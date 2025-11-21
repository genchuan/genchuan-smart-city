package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.evalsubject;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 评价主体管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_subject")
@KeySequence("sys_eval_subject_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalSubjectDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 评价主体ID
     */
    private String evalSubjectId;
    /**
     * 评价主体名称
     */
    private String evalSubjectName;
    /**
     * 主体编码
     */
    private String subjectCode;
    /**
     * 主体类型
     */
    private String subjectType;
    /**
     * 所属部门ID
     */
    private String deptId;
    /**
     * 所属部门名称
     */
    private String deptName;
    /**
     * 成员IDs
     */
    private String memberIds;
    /**
     * 成员姓名
     */
    private String memberNames;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 主体状态
     */
    private String subjectStatus;
    /**
     * 创建人(业务)
     */
    private String createUserBiz;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
    /**
     * 更新人(业务)
     */
    private String updateUserBiz;
    /**
     * 更新时间(业务)
     */
    private LocalDateTime updateTimeBiz;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}
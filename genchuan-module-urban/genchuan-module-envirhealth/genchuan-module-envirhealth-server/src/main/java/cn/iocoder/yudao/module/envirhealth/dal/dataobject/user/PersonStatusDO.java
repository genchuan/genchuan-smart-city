package cn.iocoder.yudao.module.envirhealth.dal.dataobject.user;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 人员状态字典 DO
 *
 * @author 芋道源码
 */
@TableName("sys_person_status")
@KeySequence("sys_person_status_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonStatusDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String personStatusId;
    /**
     * 状态名称（可选值：在岗/休假/请假/离职/待入职/调岗/停薪留职）
     */
    private String name;
    /**
     * 状态编码
     */
    private String statusCode;
    /**
     * 排序号
     */
    private Integer sort;
}
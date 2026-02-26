package cn.iocoder.yudao.module.envir.dal.dataobject.personstatus;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

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
    private String sysPersonStatusId;
    /**
     * 状态名称（可选值：在岗/休假/请假/离职/待入职/调岗/停薪留职）
     */
    private String name;
    /**
     * 状态编码
     */
    private String code;
    /**
     * 状态（可选值：0-禁用/1-启用）
     */
    private Integer status;
    /**
     * 排序号
     */
    private Integer sort;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}
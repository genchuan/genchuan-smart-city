package cn.iocoder.yudao.module.envir.dal.dataobject.dept;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 部门 DO
 *
 * @author 芋道源码
 */
@TableName("sys_dept")
@KeySequence("sys_dept_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String sysDeptId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 父部门ID（关联自身sys_dept_id，顶级部门填0）
     */
    private String parentId;
    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 状态（可选值：0-禁用/1-启用）
     */
    private Integer status;
    /**
     * 排序号
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;
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
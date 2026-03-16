package cn.iocoder.yudao.module.envirhealth.dal.dataobject.planstatus;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 计划状态字典 DO
 *
 * @author 芋道源码
 */
@TableName("sys_plan_status")
@KeySequence("sys_plan_status_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanStatusDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 计划状态主键（UUID）
     */
    private String sysPlanStatusId;
    /**
     * 状态名称
     */
    private String name;
    /**
     * 状态编码
     */
    private String code;
    /**
     * 状态：1-启用/0-禁用
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;

}
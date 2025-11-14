package cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopflowcfg;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 联动流程配置 DO
 *
 * @author 亘川智城
 */
@TableName("biz_coop_flow_cfg")
@KeySequence("biz_coop_flow_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoopFlowCfgDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 配置ID，唯一编码，UUID生成
     */
    private String flowCfgId;
    /**
     * 配置名称，如“火灾救援联动流程配置”
     */
    private String flowCfgName;
    /**
     * 流程步骤，详细描述联动流程的各个步骤
     */
    private String flowSteps;
    /**
     * 创建人账号，关联用户信息表
     */
    private String createUser;
    /**
     * 更新人账号，关联用户信息表
     */
    private String updateUser;

}
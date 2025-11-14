package cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopcommand;

import lombok.*;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 联动指令 DO
 *
 * @author 亘川智城
 */
@TableName("biz_coop_command")
@KeySequence("biz_coop_command_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoopCommandDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 指令ID，唯一编码，UUID生成
     */
    private String commandId;
    /**
     * 指令编号，系统自动生成，格式“CMD+年份（4位）+6位流水号”
     */
    private String commandNo;
    /**
     * 指令名称，如“XX火灾救援联动指令”
     */
    private String commandName;
    /**
     * 关联流程ID，关联联动流程配置表
     */
    private String flowId;
    /**
     * 关联事件编码，关联事件接报登记表
     */
    private String evtCode;
    /**
     * 联动单位ID，关联联动单位信息表
     */
    private String unitId;

}
package cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtwo;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件工单 DO
 *
 * @author 亘川智城
 */
@TableName("biz_evt_wo")
@KeySequence("biz_evt_wo_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvtWoDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 工单ID，唯一编码，UUID生成
     */
    private String woId;
    /**
     * 工单编号，系统自动生成，格式“WO+年份(4位)+月份(2位)+8位流水号”
     */
    private String woNo;
    /**
     * 事件编码，关联事件分级分拨表
     */
    private String evtCode;
    /**
     * 事件类型名称，从分拨表同步
     */
    private String evtTypeName;
    /**
     * 分拨部门代码，从分拨表同步
     */
    private String allocateDeptCode;
    /**
     * 分拨部门名称，从分拨表同步
     */
    private String allocateDeptName;
    /**
     * 工单状态，待分配/处置中/已办结/已驳回，默认待分配
     */
    private String woStatus;
    /**
     * 负责人ID，处置工作人员账号，关联用户信息表(sys_user)
     */
    private String chargerId;
    /**
     * 负责人姓名，与负责人ID同步
     */
    private String chargerName;

}
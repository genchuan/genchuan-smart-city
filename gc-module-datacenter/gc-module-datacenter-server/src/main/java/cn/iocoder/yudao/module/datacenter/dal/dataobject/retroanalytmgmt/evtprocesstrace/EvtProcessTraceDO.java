package cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.evtprocesstrace;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件过程回溯 DO
 *
 * @author 亘川智城
 */
@TableName("biz_evt_process_trace")
@KeySequence("biz_evt_process_trace_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvtProcessTraceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 回溯ID，唯一编码，UUID生成
     */
    private String traceId;
    /**
     * 事件编码，关联事件接报登记表
     */
    private String evtCode;
    /**
     * 事件名称，与事件编码同步，来自事件接报登记表
     */
    private String evtName;
    /**
     * 事件类型名称，与事件编码同步，来自事件类型配置表
     */
    private String evtTypeName;
    /**
     * 接报环节，接报时间、接报人、事发位置、描述，如“2025-10-01 08:00，张三，XX路内涝”
     */
    private String rptLink;
    /**
     * 研判环节，研判时间、研判人、原因、紧急程度，如“2025-10-01 08:30，李四，降雨量过大，紧急”
     */
    private String analysisLink;
    /**
     * 分拨环节，分拨时间、分拨人、分拨部门，如“2025-10-01 09:00，王五，应急管理局”
     */
    private String allocateLink;
    /**
     * 工单环节，工单编号、处置人、状态流转，如“WO20251000001，赵六，待分配→执行中→已完成”
     */
    private String woLink;
    /**
     * 调度环节，调度方案/指令、资源类型/数量、负责人，如“DISP2025000001，冲锋舟5辆，孙七”
     */
    private String dispatchLink;
    /**
     * 回溯人ID，回溯报告生成人账号，用户信息表（sys_user）
     */
    private String traceUserId;
    /**
     * 回溯人姓名，与回溯人ID同步，用户信息表（sys_user）
     */
    private String traceUserName;
    /**
     * 回溯时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成
     */
    private LocalDateTime traceTime;
    /**
     * 回溯报告路径，PDF格式回溯报告存储路径
     */
    private String rptPath;
    /**
     * 分类扩展字段1，预留，如“回溯批次”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“回溯批次”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储回溯备注
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储回溯备注
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}
package cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtrptreg;

import lombok.*;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件接报登记 DO
 *
 * @author 亘川智城
 */
@TableName("biz_evt_rpt_reg")
@KeySequence("biz_evt_rpt_reg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvtRptRegDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 接报ID，唯一编码，UUID
     */
    private String rptId;
    /**
     * 事件编码，系统自动生成，格式“6位区域码+2位大类码+3位小类码+7位顺序码”
     */
    private String evtCode;
    /**
     * 事件类型ID，关联事件类型配置表小类ID
     */
    private String evtTypeId;
    /**
     * 事件类型名称，与类型ID同步
     */
    private String evtTypeName;
    /**
     * 事发区域代码，关联行政区划表
     */
    private String incidentRegionCode;
    /**
     * 事发区域名称，与区域代码同步
     */
    private String incidentRegionName;
    /**
     * 事发位置，详细位置，如“XX路与XX路交叉口东北侧”
     */
    private String incidentLocation;
    /**
     * 事发坐标X，度，2000国家大地坐标系经度
     */
    private BigDecimal incidentCoordX;
    /**
     * 事发坐标Y，度，2000国家大地坐标系纬度
     */
    private BigDecimal incidentCoordY;
    /**
     * 事件描述，事件详情，如“商贩占用人行道售卖水果”
     */
    private String evtDesc;

}
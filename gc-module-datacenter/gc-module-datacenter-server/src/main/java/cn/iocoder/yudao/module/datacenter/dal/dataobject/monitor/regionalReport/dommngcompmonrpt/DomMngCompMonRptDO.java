package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommngcompmonrpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件分域监测报表 DO
 *
 * @author zcq
 */
@TableName("stat_dom_mng_comp_mon_rpt")
@KeySequence("stat_dom_mng_comp_mon_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomMngCompMonRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 报表ID
     */
    private String rptId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 统计周期名称
     */
    private String statCycleName;
    /**
     * 行政区划代码
     */
    private String adminCode;
    /**
     * 行政区划名称
     */
    private String adminName;
    /**
     * 管理部件大类ID
     */
    private String mngCompMajorId;
    /**
     * 管理部件大类名称
     */
    private String mngCompMajorName;
    /**
     * 管理部件小类ID
     */
    private String mngCompMinorId;
    /**
     * 管理部件小类名称
     */
    private String mngCompMinorName;
    /**
     * 分域ID
     */
    private String domId;
    /**
     * 分域名称
     */
    private String domName;
    /**
     * 关联点位数量
     */
    private Integer relPtCount;
    /**
     * 关联设备数量
     */
    private Integer relDeviceCount;
    /**
     * 设备在线率
     */
    private BigDecimal deviceOnlineRate;
    /**
     * 部件故障事件数
     */
    private Integer compFaultEvtCount;
    /**
     * 故障修复率
     */
    private BigDecimal faultRepairRate;
    /**
     * 报表生成时间
     */
    private LocalDateTime rptCreateTime;
    /**
     * 报表生成人
     */
    private Long rptCreateUser;
    /**
     * 报表备注
     */
    private String rptRemark;
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
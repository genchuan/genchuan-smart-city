package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonidxrpt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 基础监测指标报 DO
 *
 * @author zhucongquan
 */
@TableName("stat_basic_mon_idx_rpt")
@KeySequence("stat_basic_mon_idx_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicMonIdxRptDO extends BaseDO {

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
     * 点位类型
     */
    private String ptType;
    /**
     * 点位总数(个)
     */
    private Integer totalPtCount;
    /**
     * 启用点位数量(个)
     */
    private Integer enablePtCount;
    /**
     * 停用点位数量(个)
     */
    private Integer disablePtCount;
    /**
     * 新增点位数量(个)
     */
    private Integer newPtCount;
    /**
     * 关联设备总数(个)
     */
    private Integer relDeviceCount;
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
package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonidx;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 基础监测指标 DO
 *
 * @author zcq
 */
@TableName("stat_basic_mon_idx")
@KeySequence("stat_basic_mon_idx_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicMonIdxDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 指标ID
     */
    private String idxId;
    /**
     * 指标名称
     */
    private String idxName;
    /**
     * 指标编码
     */
    private String idxCode;
    /**
     * 指标单位
     */
    private String idxUnit;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 数据长度
     */
    private String dataLength;
    /**
     * 关联点位类型
     */
    private String relPtType;
    /**
     * 预警阈值上限
     */
    private String warnThresholdMax;
    /**
     * 预警阈值下限
     */
    private String warnThresholdMin;
    /**
     * 指标说明
     */
    private String idxDesc;
    /**
     * 指标状态
     */
    private Boolean idxStatus;
    /**
     * 创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 更新时间
     */
    private LocalDateTime updateTimeSys;
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
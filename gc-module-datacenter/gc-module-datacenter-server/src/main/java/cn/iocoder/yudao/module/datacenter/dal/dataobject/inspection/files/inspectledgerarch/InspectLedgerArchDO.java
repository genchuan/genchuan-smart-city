package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectledgerarch;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检台账档案 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_ledger_arch")
@KeySequence("biz_inspect_ledger_arch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectLedgerArchDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 档案ID
     */
    private String ledgerArchId;
    /**
     * 档案编码
     */
    private String ledgerArchCode;
    /**
     * 档案名称
     */
    private String ledgerArchName;
    /**
     * 所属区域代码
     */
    private String areaCode;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 台账周期
     */
    private String ledgerCycle;
    /**
     * 核心指标JSON
     */
    private String coreIdxJson;
    /**
     * 包含档案类型
     */
    private String includeArchTypes;
    /**
     * 台账附件URL
     */
    private String ledgerAttachUrl;
    /**
     * 归档人
     */
    private String ledgerArchUser;
    /**
     * 归档时间
     */
    private LocalDateTime ledgerArchTime;
    /**
     * 档案状态
     */
    private String ledgerArchStatus;
    /**
     * 销毁时间
     */
    private LocalDateTime ledgerDestroyTime;
    /**
     * 归档说明
     */
    private String ledgerArchDesc;
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
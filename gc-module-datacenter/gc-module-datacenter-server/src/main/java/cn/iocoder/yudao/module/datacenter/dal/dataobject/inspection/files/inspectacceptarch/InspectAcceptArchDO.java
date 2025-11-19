package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectacceptarch;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检验收档案 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_accept_arch")
@KeySequence("biz_inspect_accept_arch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectAcceptArchDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 档案ID
     */
    private String acceptArchId;
    /**
     * 档案编码
     */
    private String acceptArchCode;
    /**
     * 档案名称
     */
    private String acceptArchName;
    /**
     * 所属区域代码
     */
    private String areaCode;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 归档周期
     */
    private String acceptArchCycle;
    /**
     * 包含验收记录数
     */
    private Integer includeAcceptCount;
    /**
     * 验收合格率
     */
    private BigDecimal acceptQualifiedRate;
    /**
     * 档案附件URLs
     */
    private String acceptAttachUrls;
    /**
     * 归档人
     */
    private String acceptArchUser;
    /**
     * 归档时间
     */
    private LocalDateTime acceptArchTime;
    /**
     * 档案状态
     */
    private String acceptArchStatus;
    /**
     * 销毁时间
     */
    private LocalDateTime acceptDestroyTime;
    /**
     * 归档说明
     */
    private String acceptArchDesc;
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
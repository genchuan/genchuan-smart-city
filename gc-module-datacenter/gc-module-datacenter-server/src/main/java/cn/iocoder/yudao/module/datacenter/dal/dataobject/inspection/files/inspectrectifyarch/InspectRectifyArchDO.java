package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectrectifyarch;

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
 * 巡查巡检整改档案 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_rectify_arch")
@KeySequence("biz_inspect_rectify_arch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectRectifyArchDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 档案ID
     */
    private String rectifyArchId;
    /**
     * 档案编码
     */
    private String rectifyArchCode;
    /**
     * 档案名称
     */
    private String rectifyArchName;
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
    private String rectifyArchCycle;
    /**
     * 关联问题类型
     */
    private String problemTypeName;
    /**
     * 包含整改任务数
     */
    private Integer includeTaskCount;
    /**
     * 整改完成率
     */
    private BigDecimal rectifyCompleteRate;
    /**
     * 档案附件URLs
     */
    private String rectifyAttachUrls;
    /**
     * 归档人
     */
    private String rectifyArchUser;
    /**
     * 归档时间
     */
    private LocalDateTime rectifyArchTime;
    /**
     * 档案状态
     */
    private String rectifyArchStatus;
    /**
     * 销毁时间
     */
    private LocalDateTime rectifyDestroyTime;
    /**
     * 归档说明
     */
    private String rectifyArchDesc;
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
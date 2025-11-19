package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectrecarch;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检记录档案 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_rec_arch")
@KeySequence("biz_inspect_rec_arch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectRecArchDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 档案ID
     */
    private String archId;
    /**
     * 档案编码
     */
    private String archCode;
    /**
     * 档案名称
     */
    private String archName;
    /**
     * 档案类型
     */
    private String archType;
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
    private String archCycle;
    /**
     * 包含记录数
     */
    private Integer includeRecCount;
    /**
     * 档案附件URLs
     */
    private String archAttachUrls;
    /**
     * 归档人
     */
    private String archUser;
    /**
     * 归档时间
     */
    private LocalDateTime archTime;
    /**
     * 档案状态
     */
    private String archStatus;
    /**
     * 销毁时间
     */
    private LocalDateTime destroyTime;
    /**
     * 归档说明
     */
    private String archDesc;
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
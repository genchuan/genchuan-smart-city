package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivadmin;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 行政区划分域 DO
 *
 * @author zcq
 */
@TableName("stat_dom_div_admin")
@KeySequence("stat_dom_div_admin_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomDivAdminDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分域ID
     */
    private String domId;
    /**
     * 分域名称
     */
    private String domName;
    /**
     * 分域编码
     */
    private String domCode;
    /**
     * 行政区域级别
     */
    private String adminLevel;
    /**
     * 行政区域代码
     */
    private String adminCode;
    /**
     * 行政区域名称
     */
    private String adminName;
    /**
     * 关联点位数量(个)
     */
    private Integer relPtCount;
    /**
     * 关联设备数量(个)
     */
    private Integer relDeviceCount;
    /**
     * 分域负责人ID
     */
    private Long mngrId;
    /**
     * 分域负责人姓名
     */
    private String mngrName;
    /**
     * 分域状态
     */
    private Boolean domStatus;
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
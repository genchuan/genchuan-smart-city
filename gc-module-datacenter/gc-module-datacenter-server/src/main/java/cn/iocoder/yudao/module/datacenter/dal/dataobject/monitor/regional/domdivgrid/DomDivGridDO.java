package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivgrid;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 网格分域 DO
 *
 * @author zcq
 */
@TableName("stat_dom_div_grid")
@KeySequence("stat_dom_div_grid_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomDivGridDO extends BaseDO {

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
     * 网格类型
     */
    private String gridType;
    /**
     * 网格ID
     */
    private String gridId;
    /**
     * 网格编码
     */
    private String gridCode;
    /**
     * 网格名称
     */
    private String gridName;
    /**
     * 所属行政区域代码
     */
    private String adminCode;
    /**
     * 所属行政区域名称
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
     * 网格员ID
     */
    private Long gridUserId;
    /**
     * 网格员姓名
     */
    private String gridUserName;
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
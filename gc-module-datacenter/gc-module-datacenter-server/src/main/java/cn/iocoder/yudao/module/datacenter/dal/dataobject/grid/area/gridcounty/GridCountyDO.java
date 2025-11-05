package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridcounty;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 县级及以上行政区划配置 DO
 *
 * @author zcq
 */
@TableName("gc_grid_county")
@KeySequence("gc_grid_county_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridCountyDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 行政区划ID
     */
    private String adminDivisionId;
    /**
     * 行政区划代码
     */
    private String adminCode;
    /**
     * 行政区划名称
     */
    private String adminName;
    /**
     * 行政区划级别(1-省级,2-市级,3-县级)
     */
    private Integer adminLevel;
    /**
     * 上级行政区划ID
     */
    private String parentAdminId;
    /**
     * 生效时间
     */
    private LocalDateTime effectiveTime;
    /**
     * 失效时间
     */
    private LocalDateTime invalidTime;
    /**
     * 备注
     */
    private String remark;

}
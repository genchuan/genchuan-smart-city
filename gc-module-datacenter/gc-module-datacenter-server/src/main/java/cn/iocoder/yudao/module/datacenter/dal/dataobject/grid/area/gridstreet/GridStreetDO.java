package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridstreet;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 街道（镇、乡）行政区划配置 DO
 *
 * @author zcq
 */
@TableName("gc_grid_street")
@KeySequence("gc_grid_street_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridStreetDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 街道ID
     */
    private String streetId;
    /**
     * 街道代码
     */
    private String streetCode;
    /**
     * 街道名称
     */
    private String streetName;
    /**
     * 所属县级行政区ID
     */
    private String countyAdminId;
    /**
     * 街道级别(1-街道,2-镇,3-乡)
     */
    private Integer streetLevel;
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
package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridcommunity;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 社区（村）行政区划配置 DO
 *
 * @author zcq
 */
@TableName("gc_grid_community")
@KeySequence("gc_grid_community_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridCommunityDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 社区ID
     */
    private String communityId;
    /**
     * 社区代码
     */
    private String communityCode;
    /**
     * 社区名称
     */
    private String communityName;
    /**
     * 所属街道ID
     */
    private String streetId;
    /**
     * 生效时间
     */
    private LocalDateTime effectiveTime;
    /**
     * 失效时间
     */
    private LocalDateTime invalidTime;
    /**
     * 社区边界坐标
     */
    private String communityBoundary;
    /**
     * 备注
     */
    private String remark;

}
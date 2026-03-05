package cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.roadside;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 路侧泊位 DO
 *
 * @author zhucongquan
 */
@TableName("park_roadside")
@KeySequence("park_roadside_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkRoadsideDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联ID
     */
    private String assetExtendId;
    /**
     * 道路名称
     */
    private String roadName;
    /**
     * 唯一泊位编号
     */
    private String berthNumber;
    /**
     * 泊位类型
     */
    private String berthType;
    /**
     * 所属计费桩
     */
    private String feePileId;
    /**
     * 占用状态
     */
    private String status;
    /**
     * 上次占用时间
     */
    private LocalDateTime lastOccupyTime;
    /**
     * 上次释放时间
     */
    private LocalDateTime lastReleaseTime;
    /**
     * 业务创建时间
     */
    private LocalDateTime roadsideCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime roadsideUpdateTime;
    /**
     * 业务备注
     */
    private String roadsideRemark;

}
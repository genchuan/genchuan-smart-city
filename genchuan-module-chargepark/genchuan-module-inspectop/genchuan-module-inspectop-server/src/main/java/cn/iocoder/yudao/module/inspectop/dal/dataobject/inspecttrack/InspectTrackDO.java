package cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttrack;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡检轨迹 DO
 *
 * @author zhucongquan
 */
@TableName("inspect_track")
@KeySequence("inspect_track_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectTrackDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 巡检人员ID
     */
    private Long userId;
    /**
     * 轨迹时间
     */
    private LocalDateTime trackTime;
    /**
     * 巡检里程（公里）
     */
    private BigDecimal mileage;
    /**
     * 巡检时长（分钟）
     */
    private Integer duration;
    /**
     * 所属片区
     */
    private String area;
    /**
     * 轨迹状态
     */
    private String status;
    /**
     * 轨迹点
     */
    private String points;
    /**
     * 核查状态
     */
    private String checkStatus;
    /**
     * 核查备注
     */
    private String checkRemark;

    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
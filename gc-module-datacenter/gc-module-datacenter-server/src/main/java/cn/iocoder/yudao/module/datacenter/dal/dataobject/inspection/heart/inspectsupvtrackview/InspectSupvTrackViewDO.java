package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvtrackview;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检督办进度跟踪视图 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_supv_track_view")
@KeySequence("biz_inspect_supv_track_view_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectSupvTrackViewDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 督办任务ID
     */
    private String supvTaskId;
    /**
     * 督办任务编码
     */
    private String supvTaskCode;
    /**
     * 关联问题名称
     */
    private String problemName;
    /**
     * 督办人姓名
     */
    private String supvUserName;
    /**
     * 整改负责人姓名
     */
    private String leaderUserName;
    /**
     * 督办任务状态
     */
    private String supvStatus;
    /**
     * 督办截止时间
     */
    private LocalDateTime supvDeadlineTime;
    /**
     * 是否超期
     */
    private Boolean isOverdue;
    /**
     * 最新督办进展时间
     */
    private LocalDateTime latestSupvTime;
    /**
     * 最新督办进展类型
     */
    private String latestSupvType;
    /**
     * 最新督办进展内容
     */
    private String latestSupvContent;
    /**
     * 督办操作次数
     */
    private Integer supvOperCount;
    /**
     * 关联整改任务状态
     */
    private String rectifyStatus;

}
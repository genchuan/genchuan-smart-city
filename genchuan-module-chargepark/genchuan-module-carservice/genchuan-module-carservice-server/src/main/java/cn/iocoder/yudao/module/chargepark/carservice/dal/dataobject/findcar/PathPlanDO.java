package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 路径规划 DO
 *
 * 数据库表：path_plan
 *
 * @author carservice
 */
@TableName("path_plan")
@KeySequence("path_plan_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PathPlanDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 用户 ID，关联芋道用户表 system_user
     */
    private Long userId;
    /**
     * 起点位置（格式："经度,纬度"）
     */
    private String startLocation;
    /**
     * 起点位置汉字地址（供列表页展示，前端地图 SDK 选点时回传）
     */
    private String startLocationName;
    /**
     * 终点位置（格式："经度,纬度"）
     */
    private String endLocation;
    /**
     * 终点位置汉字地址（供列表页展示，前端地图 SDK 选点时回传）
     */
    private String endLocationName;
    /**
     * 规划时间
     */
    private LocalDateTime planTime;
    /**
     * 路径长度（米）
     */
    private Integer pathLength;
    /**
     * 预计时长（秒）
     */
    private Integer expectDuration;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;

}

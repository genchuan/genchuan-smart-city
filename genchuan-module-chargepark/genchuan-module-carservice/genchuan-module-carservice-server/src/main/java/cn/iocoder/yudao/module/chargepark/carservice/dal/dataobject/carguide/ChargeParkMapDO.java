package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 充停地图 DO
 *
 * 数据库表：charge_park_map
 *
 * @author carservice
 */
@TableName("charge_park_map")
@KeySequence("charge_park_map_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeParkMapDO extends BaseDO {

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
     * 查询位置（格式："经度,纬度"）
     */
    private String queryLocation;
    /**
     * 查询位置汉字地址（供列表页展示，前端地图 SDK 选点时回传）
     */
    private String queryLocationName;
    /**
     * 查询时间
     */
    private LocalDateTime queryTime;
    /**
     * 查询结果数
     */
    private Integer resultCount;
    /**
     * 响应时长（毫秒）
     */
    private Integer responseDuration;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;

}

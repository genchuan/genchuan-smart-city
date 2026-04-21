package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 周边场站 DO
 *
 * 数据库表：near_station
 *
 * @author carservice
 */
@TableName("near_station")
@KeySequence("near_station_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NearStationDO extends BaseDO {

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
     * 周边场站数
     */
    private Integer stationCount;
    /**
     * 空位场站数
     */
    private Integer emptyStationCount;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;

}

package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 周边场站查询结果快照 DO
 *
 * 数据库表：near_station_result
 * 每条 near_station 查询返回的场站列表会展开成多条 result 行,记录当时返回的具体场站、距离、空位状态。
 *
 * @author carservice
 */
@TableName("near_station_result")
@KeySequence("near_station_result_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NearStationResultDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 关联 near_station.id
     */
    private Long nearStationId;
    /**
     * 场站 ID,关联 stationresource 模块的 station_info.id
     */
    private Long stationId;
    /**
     * 场站名称(冗余存储,避免后续场站改名/删除导致快照失真)
     */
    private String stationName;
    /**
     * 场站经度
     */
    private BigDecimal lon;
    /**
     * 场站纬度
     */
    private BigDecimal lat;
    /**
     * 当时该场站到查询点的距离(km)
     */
    private BigDecimal distanceKm;
    /**
     * 当时是否有空位
     */
    private Boolean hasEmpty;
    /**
     * 当时空位数
     */
    private Integer emptyCount;
    /**
     * 当时车位总数
     */
    private Integer totalCount;

}

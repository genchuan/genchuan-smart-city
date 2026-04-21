package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 周边场站 Mock DO（过渡方案）
 *
 * 用于 carservice 在 stationresource 模块未开放 Feign RPC 前，提供 near-station 图表接口
 * 所需的场站展示数据（含坐标、名称、是否有空位、距离分桶）。
 *
 * 待 stationresource 开放 RPC 后，这张表和对应 DO/Mapper 可整体删除。
 *
 * @author carservice
 */
@TableName("mock_nearby_station")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MockNearbyStationDO extends BaseDO {

    @TableId
    private Long id;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 场站名称
     */
    private String stationName;

    /**
     * 是否有空位
     */
    private Boolean hasEmpty;

    /**
     * 空位数
     */
    private Integer emptySpace;

    /**
     * 总车位数
     */
    private Integer totalSpace;

    /**
     * 距离分桶（0-1km / 1-3km / 3-5km）
     */
    private String distanceGroup;

}

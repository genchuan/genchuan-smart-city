package cn.iocoder.yudao.module.smartcity.dal.dataobject.pointinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测点位 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_point_info")
@KeySequence("smartcity_point_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointInfoDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 点位编号
     */
    private String pointNumber;
    /**
     * 点位名称
     */
    private String pointName;
    /**
     * 点位类型
     */
    private String pointType;
    /**
     * 所属区域
     */
    private String belongingArea;
    /**
     * 经纬度坐标
     */
    private String latitudeLongitudeCoordinate;
    /**
     * 海拔高度
     */
    private String altitude;
    /**
     * 设备信息
     */
    private String deviceInfo;

}
package cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkreversesearch;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 反向寻车记录 DO
 *
 * @author lxs
 */
@TableName("park_reverse_search")
@KeySequence("park_reverse_search_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkReverseSearchDO extends BaseDO {

    /**
     * [主键ID] 反向寻车记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [用户ID] 用户唯一标识
     */
    private Long userId;
    /**
     * [车牌号码] 用户车辆车牌号码
     */
    private String carNumber;
    /**
     * [车场ID] 所属车场ID
     */
    private Long lotId;
    /**
     * [车位ID] 所属车位ID
     */
    private Long spaceId;
    /**
     * [寻车时间] 用户发起反向寻车的时间
     */
    private LocalDateTime searchTime;
    /**
     * [坐标X] 车位或定位点X坐标
     */
    private BigDecimal locationX;
    /**
     * [坐标Y] 车位或定位点Y坐标
     */
    private BigDecimal locationY;
    /**
     * [所在区域] 如：楼层/分区
     */
    private String locationArea;
    /**
     * [行政区域全码] 12位行政区域编码
     */
    private String regionFullCode;
    /**
     * [导航路径信息] JSON格式导航路径数据
     */
    private String routeInfo;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;
    /**
     * [备注] 反向寻车相关备注说明
     */
    private String remark;

}

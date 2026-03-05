package cn.iocoder.yudao.module.facility.dal.dataobject.road.roadfacility;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 道路设施 DO
 *
 * @author 亘川智城
 */
@TableName("road_facility")
@KeySequence("road_facility_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadFacilityDO extends BaseDO {

    /**
     * [主键ID] 主键，道路设施唯一标识
     */
    @TableId
    private Long id;
    /**
     * [道路编码] UUID格式
     */
    private String roadCode;
    /**
     * [路段名称] 路段名称
     */
    private String roadName;
    /**
     * [所属区域编码] 12位地区码（GB/T 2260），关联sys_area.full_code
     */
    private String areaCode;
    /**
     * [所在地区名称]
     */
    private String areaName;
    /**
     * [路段长度] 路段长度，数值
     */
    private BigDecimal length;
    /**
     * [路段宽度] 路段宽度，数值
     */
    private BigDecimal width;
    /**
     * [建成时间] 建成时间
     */
    private LocalDate buildTime;
    /**
     * [使用状态] 如:正常/维修中/废弃
     */
    private String status;
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

}

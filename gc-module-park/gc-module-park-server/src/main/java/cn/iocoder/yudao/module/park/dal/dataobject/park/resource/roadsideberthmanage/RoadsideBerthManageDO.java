package cn.iocoder.yudao.module.park.dal.dataobject.park.resource.roadsideberthmanage;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 路测泊位管理 DO
 *
 * @author zhucongquan
 */
@TableName("park_roadside_berth_manage")
@KeySequence("park_roadside_berth_manage_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadsideBerthManageDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 车场ID
     */
    private String parkId;

    /**
     * 泊位编号
     */
    private String berthCode;
    /**
     * 路段名称
     */
    private String roadName;
    /**
     * 位置描述
     */
    private String locationDesc;
    /**
     * 泊位类型
     */
    private String berthType;
    /**
     * 坐标X
     */
    private BigDecimal coordinateX;
    /**
     * 坐标Y
     */
    private BigDecimal coordinateY;
    /**
     * 当前车辆
     */
    private String currentCar;
    /**
     * 启用状态
     */
    private String berthStatus;
    /**
     * 所属行政区划代码
     */
    private String areaCode;
    /**
     * 路侧管理信息
     */
    private String roadsideInfo;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}

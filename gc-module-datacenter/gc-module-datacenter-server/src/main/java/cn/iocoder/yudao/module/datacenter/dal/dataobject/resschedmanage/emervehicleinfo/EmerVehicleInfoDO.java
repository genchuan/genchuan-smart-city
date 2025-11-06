package cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emervehicleinfo;

import lombok.*;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 救援车辆信息 DO
 *
 * @author 亘川智城
 */
@TableName("biz_emer_vehicle_info")
@KeySequence("biz_emer_vehicle_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmerVehicleInfoDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 车辆ID，唯一编码，UUID
     */
    private String vehicleId;
    /**
     * 车辆编码，系统自动生成
     */
    private String vehicleCode;
    /**
     * 车辆名称，标准名称
     */
    private String vehicleName;
    /**
     * 分类ID，关联救援车辆分类配置表
     */
    private String catId;
    /**
     * 分类名称，与分类ID同步
     */
    private String catName;
    /**
     * 适用区域代码，关联行政区划表
     */
    private String applyRegionCode;
    /**
     * 适用区域名称，与区域代码同步
     */
    private String applyRegionName;
    /**
     * 车辆型号，如“XX型救援车”
     */
    private String vehicleModel;
    /**
     * 车辆数量，该类型车辆的总数量
     */
    private Integer vehicleCount;
    /**
     * 可用数量，可投入救援的车辆数量
     */
    private Integer availableVehicleCount;
    /**
     * 车辆状态，1（正常）/0（异常），默认1
     */
    private String vehicleStatus;
    /**
     * 创建人，创建人账号，关联用户信息表(sys_user)
     */
    private String createUser;
    /**
     * 更新人，更新人账号，关联用户信息表(sys_user)
     */
    private String updateUser;

}
package cn.iocoder.yudao.module.accessmgmt.dal.dataobject.parkingmgmt.vehicleaccess;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 车辆通行 DO
 *
 * @author 亘川智城
 */
@TableName("vehicle_access")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAccessDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [车牌号]
     */
    private String plateNo;
    /**
     * [车辆类型] 如:小型车/大型车
     */
    private String vehicleType;
    /**
     * [停车场名称]
     */
    private String parkName;
    /**
     * [通行时间]
     */
    private LocalDateTime accessTime;
    /**
     * [通行状态] 如:进场中/出场中/已离场
     */
    private String accessStatus;
    /**
     * [停车时长] 单位分钟
     */
    private Integer parkDuration;
    /**
     * [费用金额]
     */
    private BigDecimal feeAmount;
    /**
     * [缴费状态] 如:已缴费/未缴费
     */
    private String payStatus;
    /**
     * [操作人账号]
     */
    private String handleUser;
    /**
     * [备用字段1] 拦截原因
     */
    private String reserve1;
    /**
     * [备用字段2] 备用字段2
     */
    private String reserve2;

}

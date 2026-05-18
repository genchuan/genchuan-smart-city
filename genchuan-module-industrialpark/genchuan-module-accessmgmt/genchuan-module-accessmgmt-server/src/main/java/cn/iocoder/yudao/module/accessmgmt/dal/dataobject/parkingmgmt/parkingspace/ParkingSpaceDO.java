package cn.iocoder.yudao.module.accessmgmt.dal.dataobject.parkingmgmt.parkingspace;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 车位信息 DO
 *
 * @author 亘川智城
 */
@TableName("parking_space")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpaceDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [车位编号]
     */
    private String spaceCode;
    /**
     * [停车场名称]
     */
    private String parkName;
    /**
     * [车位类型] 如:固定/临时
     */
    private String spaceType;
    /**
     * [车位状态] 如:空闲/占用/预约中
     */
    private String spaceStatus;
    /**
     * [租用信息]
     */
    private String rentInfo;
    /**
     * [预约用户]
     */
    private String orderUser;
    /**
     * [使用时长] 单位分钟
     */
    private Integer useDuration;
    /**
     * [操作人账号]
     */
    private String handleUser;
    /**
     * [备用字段1]
     */
    private String reserve1;
    /**
     * [备用字段2]
     */
    private String reserve2;

}

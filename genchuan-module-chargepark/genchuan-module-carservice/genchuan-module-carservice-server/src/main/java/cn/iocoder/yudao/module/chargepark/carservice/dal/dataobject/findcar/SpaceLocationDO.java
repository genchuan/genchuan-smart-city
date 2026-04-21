package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车位定位 DO
 *
 * 数据库表：space_location
 *
 * @author carservice
 */
@TableName("space_location")
@KeySequence("space_location_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceLocationDO extends BaseDO {

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
     * 车牌号码
     */
    private String plateNo;
    /**
     * 查询时间
     */
    private LocalDateTime queryTime;
    /**
     * 车位 ID，关联车位模块车位表
     */
    private Long spaceId;
    /**
     * 定位结果：成功/失败
     * 关联字典 space_location_location_result
     */
    private String locationResult;
    /**
     * 响应时长（毫秒）
     */
    private Integer responseDuration;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;

}

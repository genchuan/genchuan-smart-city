package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.endpark;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 结束停车 DO
 *
 * @author 亘川智城
 */
@TableName("end_park")
@KeySequence("end_park_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndParkDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 车牌
     */
    private String plateNo;
    /**
     * 车位ID，关联车位表
     */
    private Long spaceId;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 缴费状态：待支付/已支付/已取消，关联字典end_park_status
     */
    private String status;
    /**
     * 片区ID，关联片区表
     */
    private Long areaId;
    /**
     * 操作人ID，关联芋道用户表system_user
     */
    private Long operatorId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
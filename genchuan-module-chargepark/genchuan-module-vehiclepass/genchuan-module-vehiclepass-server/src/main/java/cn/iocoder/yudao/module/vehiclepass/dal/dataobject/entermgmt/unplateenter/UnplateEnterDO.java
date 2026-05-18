package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.unplateenter;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 无牌入场 DO
 *
 * @author 亘川智城
 */
@TableName("unplate_enter")
@KeySequence("unplate_enter_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnplateEnterDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 车辆类型：小型车/中型车/大型车/其他，关联字典unplate_enter_car_type
     */
    private String carType;
    /**
     * 车辆颜色
     */
    private String carColor;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 登记时间
     */
    private LocalDateTime registerTime;
    /**
     * 审核状态：待审核/已通过/已驳回，关联字典unplate_enter_status
     */
    private String status;
    /**
     * 场站ID，关联场站表
     */
    private Long stationId;
    /**
     * 场站名称
     */
    private String stationName;
    /**
     * 审核人ID，关联system_user用户表
     */
    private Long auditUserId;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 审核意见
     */
    private String auditComment;
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
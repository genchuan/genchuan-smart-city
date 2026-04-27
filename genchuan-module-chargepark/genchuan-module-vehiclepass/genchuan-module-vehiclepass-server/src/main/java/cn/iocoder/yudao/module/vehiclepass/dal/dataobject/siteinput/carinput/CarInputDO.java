package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.carinput;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车辆录入 DO
 *
 * @author 亘川智城
 */
@TableName("car_input")
@KeySequence("car_input_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarInputDO extends BaseDO {

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
     * 录入时间
     */
    private LocalDateTime inputTime;
    /**
     * 审核状态：待审核/已通过/已驳回，关联字典car_input_status
     */
    private String status;
    /**
     * 片区ID，关联片区表
     */
    private Long areaId;
    /**
     * 录入人ID，关联芋道用户表system_user
     */
    private Long inputUserId;
    /**
     * 审核人ID，关联芋道用户表system_user
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
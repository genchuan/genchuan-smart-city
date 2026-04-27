package cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupcar;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 集团车辆 DO
 *
 * @author 亘川智城
 */
@TableName("group_car")
@KeySequence("group_car_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupCarDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 所属集团ID，关联group_info.id
     */
    private Long groupId;
    /**
     * 车牌号码，唯一
     */
    private String plateNo;
    /**
     * 车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌
     */
    private String plateColor;
    /**
     * 车辆类型：小型车/大型车/新能源/其他
     */
    private String carType;
    /**
     * 绑定时间
     */
    private LocalDateTime bindTime;
    /**
     * 绑定状态：待审核/已绑定/已解绑
     */
    private String status;
    /**
     * 审核人ID，关联system_user.id
     */
    private Long auditorId;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 审核备注
     */
    private String auditRemark;
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
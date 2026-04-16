package cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.plateauth;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车牌认证 DO
 *
 * @author 亘川智城
 */
@TableName("plate_auth")
@KeySequence("plate_auth_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlateAuthDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 所属用户ID，关联 user_info 表 id
     */
    private Long userId;
    /**
     * 用户名称
     */
    @TableField(exist = false)
    private String nickname;
    /**
     * 关联车辆ID，关联 user_car 表 id
     */
    private Long carId;
    /**
     * 车牌号码
     */
    private String plateNo;
    /**
     * 行驶证图片地址
     */
    private String drivingLicense;
    /**
     * 认证申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 认证状态：待审核/已认证/已驳回，关联芋道字典表 plate_auth_status
     */
    private String status;
    /**
     * 审核人ID，关联芋道用户表 system_user
     */
    private Long auditorId;
    /**
     * 审核人名称
     */
    @TableField(exist = false)
    private String auditorName;
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
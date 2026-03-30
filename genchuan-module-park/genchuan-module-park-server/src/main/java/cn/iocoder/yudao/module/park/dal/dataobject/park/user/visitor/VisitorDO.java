package cn.iocoder.yudao.module.park.dal.dataobject.park.user.visitor;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 访客 DO
 *
 * @author 亘川智城
 */
@TableName("park_visitor")
@KeySequence("park_visitor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitorDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [访客姓名]
     */
    private String visitorName;
    /**
     * [手机号]
     */
    private String phone;
    /**
     * [身份证号] 脱敏存储
     */
    private String idCard;
    /**
     * [访问资源ID] 关联tb_asset_extend.asset_extend_id
     */
    private Long visitResourceId;
    /**
     * [访问事由]
     */
    private String visitReason;
    /**
     * [访问时间]
     */
    private LocalDateTime visitTime;
    /**
     * [预计离开时间]
     */
    private LocalDateTime expectLeaveTime;
    /**
     * [实际离开时间] 可为NULL
     */
    private LocalDateTime leaveTime;
    /**
     * [登记人ID] 关联park_user.id
     */
    private Long registerBy;
    /**
     * [登记时间]
     */
    private LocalDateTime registerTime;
    /**
     * [状态] 如:在访/已离场/未入场
     */
    private String status;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}

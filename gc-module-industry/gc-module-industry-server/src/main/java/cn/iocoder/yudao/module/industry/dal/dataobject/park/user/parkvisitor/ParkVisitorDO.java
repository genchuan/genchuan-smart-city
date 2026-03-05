package cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkvisitor;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 访客 DO
 *
 * @author lxs
 */
@TableName("park_visitor")
@KeySequence("park_visitor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkVisitorDO extends BaseDO {

    /**
     * 主键ID[访客唯一标识]
     */
    @TableId
    private Long id;
    /**
     * 访客姓名[访客真实姓名]
     */
    private String visitorName;
    /**
     * 手机号[访客手机号]
     */
    private String phone;
    /**
     * 身份证号[访客身份证号]
     */
    private String idCard;
    /**
     * 访问资源ID[访问资源标识，关联 tb_asset_extend.asset_extend_id]
     */
    private Long visitAssetId;
    /**
     * 访问事由[本次访问的具体事由说明]
     */
    private String visitReason;
    /**
     * 访问时间[访客进入访问的时间]
     */
    private LocalDateTime visitTime;
    /**
     * 离开时间[访客离开访问资源的时间]
     */
    private LocalDateTime leaveTime;
    /**
     * 状态[待审核/已通过/已拒绝/已结束]
     */
    private String status;
    /**
     * 审核人[审核人，关联 park_user.id]
     */
    private Long approveBy;
    /**
     * 审核时间[审核操作发生时间]
     */
    private LocalDateTime approveTime;
    /**
     * 通用扩展字段1[预留扩展字段]
     */
    private String extCommon1;
    /**
     * 通用扩展字段2[预留扩展字段]
     */
    private String extCommon2;
    /**
     * 通用扩展字段3[预留扩展字段]
     */
    private String extCommon3;
    /**
     * 通用扩展字段4[预留扩展字段]
     */
    private String extCommon4;
    /**
     * 备注[访客相关备注说明]
     */
    private String remark;

}

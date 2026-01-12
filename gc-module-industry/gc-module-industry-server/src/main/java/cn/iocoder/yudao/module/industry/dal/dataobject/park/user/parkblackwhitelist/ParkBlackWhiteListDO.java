package cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkblackwhitelist;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 黑白名单 DO
 *
 * @author lxs
 */
@TableName("park_black_white_list")
@KeySequence("park_black_white_list_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkBlackWhiteListDO extends BaseDO {

    /**
     * [主键ID] 黑白名单记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [名单类型] 如：黑名单/白名单
     */
    private String listType;
    /**
     * [目标类型] 如：用户/车辆
     */
    private String targetType;
    /**
     * [目标ID] 可为用户ID
     */
    private Long targetId;
    /**
     * [车牌] 车辆车牌号
     */
    private String targetCarNumber;
    /**
     * [列入原因] 被列入黑白名单原因
     */
    private String reason;
    /**
     * [生效时间] 规则生效时间
     */
    private LocalDateTime startTime;
    /**
     * [失效时间] 规则失效时间，永久有效为 NULL
     */
    private LocalDateTime endTime;
    /**
     * [状态] 如：生效/失效
     */
    private String status;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;
    /**
     * [备注] 黑白名单相关备注说明
     */
    private String remark;

}

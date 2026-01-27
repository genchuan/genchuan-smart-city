package cn.iocoder.yudao.module.park.dal.dataobject.park.user.blackwhitelist;

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
 * @author 亘川智城
 */
@TableName("park_black_white_list")
@KeySequence("park_black_white_list_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlackWhiteListDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [名单类型] 如:黑名单/白名单
     */
    private String listType;
    /**
     * [目标类型] 如:用户/车辆
     */
    private String targetType;
    /**
     * [目标ID] 用户ID/车牌号码
     */
    private String targetId;
    /**
     * [列入原因]
     */
    private String listReason;
    /**
     * [生效时间]
     */
    private LocalDateTime effectTime;
    /**
     * [失效时间] 永久有效为NULL
     */
    private LocalDateTime expireTime;
    /**
     * [状态] 如:生效中/已失效/已删除
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

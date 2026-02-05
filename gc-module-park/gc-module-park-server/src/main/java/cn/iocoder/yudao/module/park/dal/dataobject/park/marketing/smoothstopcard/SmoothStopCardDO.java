package cn.iocoder.yudao.module.park.dal.dataobject.park.marketing.smoothstopcard;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 畅停卡 DO
 *
 * @author 亘川智城
 */
@TableName("park_smooth_stop_card")
@KeySequence("park_smooth_stop_card_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmoothStopCardDO extends BaseDO {

    /**
     * [主键ID] 畅停卡唯一标识
     */
    @TableId
    private Long id;
    /**
     * [卡名称]
     */
    private String cardName;
    /**
     * [卡种类型]如:日卡/周卡/月卡/季卡/年卡
     */
    private String cardType;
    /**
     * [有效天数]
     */
    private Integer validDays;
    /**
     * [生效时间]
     */
    private LocalDateTime effectiveTime;
    /**
     * [失效时间]
     */
    private LocalDateTime expireTime;
    /**
     * [持有者ID] 关联park_user.id
     */
    private Long holderId;
    /**
     * [可绑定车牌数]
     */
    private Integer bindCarLimit;
    /**
     * [已绑定车牌号] 逗号分隔的字符串
     */
    private String boundCarNumbers;
    /**
     * [适用车场ID列表] 逗号分隔的字符串，关联park_lot.id
     */
    private String applyLotIds;
    /**
     * [卡种描述]
     */
    private String description;
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
    /**
     * [备注]
     */
    private String remark;

}

package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.ratesetting;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 费率设置 DO
 *
 * @author 亘川智城
 */
@TableName("rate_setting")
@KeySequence("rate_setting_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateSettingDO extends BaseDO {

    /**
     * [主键ID] 主键ID
     */
    @TableId
    private Long id;
    /**
     * [方案编号] 方案编号
     */
    private String rateCode;
    /**
     * [方案名称] 方案名称
     */
    private String rateName;
    /**
     * [适用场景] 适用场景
     */
    private String applyScene;
    /**
     * [费率规则] 费率规则
     */
    private String rateRule;
    /**
     * [生效时间] 生效时间
     */
    private LocalDateTime effectTime;
    /**
     * [失效时间] 失效时间
     */
    private LocalDateTime expireTime;
    /**
     * [适用场站] 适用场站
     */
    private String applyStation;
    /**
     * [适用集团] 适用集团
     */
    private String applyGroup;
    /**
     * [费率状态]如:未生效/已生效/已失效
     */
    private String rateStatus;
    /**
     * [备注] 备注
     */
    private String remark;
    /**
     * [备用字段1] 备用字段1
     */
    private String reserve1;
    /**
     * [备用字段2] 备用字段2
     */
    private String reserve2;


}

package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingratio;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 分账比例 DO
 *
 * @author 亘川智城
 */
@TableName("sharing_ratio")
@KeySequence("sharing_ratio_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharingRatioDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 方案编号，唯一
     */
    private String sharingCode;
    /**
     * 方案名称
     */
    private String sharingName;
    /**
     * 合作方
     */
    private String cooperator;
    /**
     * 分账类型
     */
    private String sharingType;
    /**
     * 分账比例（%）
     */
    private BigDecimal sharingRatio;
    /**
     * 适用场站，多个用逗号分隔
     */
    private String applyStation;
    /**
     * 适用渠道，多个用逗号分隔
     */
    private String applyChannel;
    /**
     * 生效时间
     */
    private LocalDateTime effectTime;
    /**
     * 失效时间
     */
    private LocalDateTime expireTime;
    /**
     * 分账状态：未生效/已生效/已失效
     */
    private String sharingStatus;
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
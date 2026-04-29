package cn.iocoder.yudao.module.usermerchant.dal.dataobject.creditmgmt.creditconfig;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 信用配置 DO
 *
 * @author 亘川智城
 */
@TableName("credit_config")
@KeySequence("credit_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditConfigDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 加减分规则
     */
    private String ruleDesc;
    /**
     * 等级阈值
     */
    private String levelThreshold;
    /**
     * 配置状态：未生效/已生效
     */
    private String status;
    /**
     * 生效时间
     */
    private LocalDateTime effectTime;
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
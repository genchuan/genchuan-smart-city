package cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.resourceaccount;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资源台账 DO
 *
 * @author zhucongquan
 */
@TableName("park_resource_account")
@KeySequence("park_resource_account_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkResourceAccountDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 台账ID（UUID）
     */
    private String accountId;
    /**
     * 资产类型
     */
    private String assetType;
    /**
     * 关联ID
     */
    private String assetExtendId;
    /**
     * 台账生成日期
     */
    private LocalDate accountDate;
    /**
     * 更新日期
     */
    private LocalDate accountUpdateDate;
    /**
     * 台账数据
     */
    private String dataContent;
    /**
     * 生成人ID
     */
    private Long generateBy;
    /**
     * 状态：有效/过期
     */
    private String accountStatus;
    /**
     * 业务创建时间
     */
    private LocalDateTime accountCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime accountUpdateTime;
    /**
     * 业务备注
     */
    private String accountRemark;

}
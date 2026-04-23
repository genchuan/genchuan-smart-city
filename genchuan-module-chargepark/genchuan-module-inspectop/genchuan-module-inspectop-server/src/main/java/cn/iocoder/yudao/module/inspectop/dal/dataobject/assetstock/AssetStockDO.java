package cn.iocoder.yudao.module.inspectop.dal.dataobject.assetstock;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 库存管理 DO
 *
 * @author zhucongquan
 */
@TableName("asset_stock")
@KeySequence("asset_stock_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetStockDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 资产ID
     */
    private Long assetId;
    /**
     * 当前库存
     */
    private Integer currentStock;
    /**
     * 预警阈值
     */
    private Integer warnThreshold;
    /**
     * 库存状态
     */
    private String status;
    /**
     * 所属场站ID
     */
    private Long stationId;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
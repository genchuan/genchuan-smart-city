package cn.iocoder.yudao.module.inspectop.dal.dataobject.sparestock;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 备件仓储 DO
 *
 * @author zhucongquan
 */
@TableName("spare_stock")
@KeySequence("spare_stock_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpareStockDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 备件ID
     */
    private Long spareId;
    /**
     * 备件名称
     */
    private String spareName;
    /**
     * 当前库存
     */
    private Integer currentStock;
    /**
     * 库存状态
     */
    private String status;
    /**
     * 入库时间
     */
    private LocalDateTime inTime;
    /**
     * 出库时间
     */
    private LocalDateTime outTime;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
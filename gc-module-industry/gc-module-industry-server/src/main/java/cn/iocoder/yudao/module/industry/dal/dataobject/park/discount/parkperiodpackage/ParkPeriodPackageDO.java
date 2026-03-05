package cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkperiodpackage;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 期卡套餐 DO
 *
 * @author lxs
 */
@TableName("park_period_package")
@KeySequence("park_period_package_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkPeriodPackageDO extends BaseDO {

    /**
     * [主键ID] 期卡套餐唯一标识
     */
    @TableId
    private Long id;
    /**
     * [套餐名称] 期卡套餐名称
     */
    private String packageName;
    /**
     * [套餐类型] 日卡 / 周卡 / 月卡 / 季卡 / 年卡 / 自定义
     */
    private String packageType;
    /**
     * [适用车场ID列表] JSON 格式字符串，存储车场ID集合
     */
    private String applyLotIds;
    /**
     * [适用车位类型] 普通 / 新能源 / 专用
     */
    private String spaceType;
    /**
     * [原价] 套餐原价
     */
    private BigDecimal originalPrice;
    /**
     * [售价] 套餐实际销售价格
     */
    private BigDecimal salePrice;
    /**
     * [有效天数] 套餐有效天数
     */
    private Integer validDays;
    /**
     * [状态] 上架 / 下架 / 暂停销售
     */
    private String status;
    /**
     * [销售数量] 套餐累计销售数量
     */
    private Integer salesCount;
    /**
     * [备注] 期卡套餐相关说明
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

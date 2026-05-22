package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("card_config")
@KeySequence("card_config_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardConfigDO extends BaseDO {

    @TableId
    private Long id;
    /** 卡种名称 */
    private String name;
    /** 卡种类型(日卡/周卡/月卡/季卡/年卡) */
    private String type;
    /** 适用范围(充电/停车/充停通用) */
    private String scope;
    /** 价格 */
    private BigDecimal price;
    /** 状态(未生效/已生效) */
    private String status;
    /** 审核人 */
    private Long auditorId;
    /** 审核时间 */
    private LocalDateTime auditTime;
    /** 销量 */
    private Integer saleCount;
    /** 生效时间 */
    private LocalDateTime effectTime;
    /** 卡种描述 */
    private String description;
    /** 有效天数 */
    private Integer validDays;
    private String reserve1;
    private String reserve2;
    /** 场站id **/
    private String stationId;
}

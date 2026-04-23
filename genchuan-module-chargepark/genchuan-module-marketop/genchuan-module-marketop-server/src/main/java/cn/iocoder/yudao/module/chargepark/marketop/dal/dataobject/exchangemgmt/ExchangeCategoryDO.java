package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@TableName("exchange_category")
@KeySequence("exchange_category_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeCategoryDO extends BaseDO {

    @TableId
    private Long id;
    /** 类目名称 */
    private String name;
    /** 类目描述 */
    private String description;
    /** 商品数量 */
    private Integer goodsCount;
    /** 类目状态(未生效/已生效/已禁用) */
    private String status;
    /** 审核人 */
    private Long auditorId;
    /** 审核时间 */
    private LocalDateTime auditTime;
    /** 生效时间 */
    private LocalDateTime effectTime;
    /** 排序权重 */
    private Integer sort;
    /** 适用范围(全平台/指定场站) */
    private String scope;
    private String reserve1;
    private String reserve2;

}

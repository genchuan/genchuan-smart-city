package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

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
    /** 所需积分 */
    private Integer point;
    /** 库存 */
    private Integer stock;
    /** 类目状态（未生效/已生效/已禁用） */
    private String status;
    /** 备注 */
    private String remark;
    /** 商品数 */
    private Integer productCount;

}

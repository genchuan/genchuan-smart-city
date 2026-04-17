package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("package_config")
@KeySequence("package_config_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageConfigDO extends BaseDO {

    @TableId
    private Long id;
    /** 券包名称 */
    private String name;
    /** 券包类型(新手包/节日包/日常包) */
    private String type;
    /** 包含优惠券ID列表 */
    private String couponIds;
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
    /** 券包描述 */
    private String description;
    /** 适用范围(全平台/指定场站/指定用户) */
    private String scope;
    private String reserve1;
    private String reserve2;

}

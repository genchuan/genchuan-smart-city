package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.charge;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 充电模式字典 DO
 *
 * @author 亘川智城
 */
@TableName("charge_mode")
@KeySequence("charge_mode_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeModeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 充电模式名称（直流/交流/交直流混合）
     */
    private String modeName;

}

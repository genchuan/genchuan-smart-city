package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pile;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 充电桩状态字典 DO
 *
 * @author 亘川智城
 */
@Data
@TableName("pile_status")
public class PileStatusDO extends BaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 状态名称
     */
    private String statusName;

}

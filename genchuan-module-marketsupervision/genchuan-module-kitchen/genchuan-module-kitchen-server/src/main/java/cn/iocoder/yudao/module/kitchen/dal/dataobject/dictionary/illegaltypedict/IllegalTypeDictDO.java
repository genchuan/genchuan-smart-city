package cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegaltypedict;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 违规类型字典 DO
 *
 * @author 亘川智城
 */
@TableName("illegal_type_dict")
@KeySequence("illegal_type_dict_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IllegalTypeDictDO extends BaseDO {

    /**
     * [主键ID] 违规类型唯一标识
     */
    @TableId
    private Long id;
    /**
     * [分类编码]对应AI场景告警字典的告警类型编码(alertType不唯一)
     */
    private String typeCategory;
    /**
     * [违规类型唯一编码] 对应AI场景告警字典的算法编码（aiAbilityCode唯一）
     */
    private String typeCode;
    /**
     * [违规类型名称] 对应AI场景告警字典的场景名称
     */
    private String typeName;
    /**
     * [违法行为说明]补充type_name说明
     */
    private String illegalBehaviorDescription;
    /**
     * [告警设备说明]对应AI场景告警字典的告警设备说明
     */
    private String alarmDeviceDescription;
    /**
     * [排序序号] 数值越小越靠前
     */
    private Integer sort;
    /**
     * [通用扩展字段1] 预留
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 预留
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 预留
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 预留
     */
    private String extCommon4;

}

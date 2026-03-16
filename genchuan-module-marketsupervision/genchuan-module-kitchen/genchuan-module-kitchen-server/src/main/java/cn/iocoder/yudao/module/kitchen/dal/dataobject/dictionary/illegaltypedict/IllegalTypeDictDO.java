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
     * [违规类型编码] 唯一编码
     */
    private String typeCode;
    /**
     * [违规类型名称] 如：未佩戴工牌/未穿工作服/从业人员未持健康证/操作区卫生不达标/食材存放不规范/设备未定期检修/操作流程不规范
     */
    private String typeName;
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

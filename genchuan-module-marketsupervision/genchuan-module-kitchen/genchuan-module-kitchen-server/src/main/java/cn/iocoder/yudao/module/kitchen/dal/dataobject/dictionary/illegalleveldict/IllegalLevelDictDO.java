package cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegalleveldict;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 违规等级字典 DO
 *
 * @author 亘川智城
 */
@TableName("illegal_level_dict")
@KeySequence("illegal_level_dict_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IllegalLevelDictDO extends BaseDO {

    /**
     * [主键ID] 违规等级唯一标识
     */
    @TableId
    private Long id;
    /**
     * [违规等级编码] 唯一编码
     */
    private String levelCode;
    /**
     * [违规等级名称] 如：一般/较重/严重
     */
    private String levelName;
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

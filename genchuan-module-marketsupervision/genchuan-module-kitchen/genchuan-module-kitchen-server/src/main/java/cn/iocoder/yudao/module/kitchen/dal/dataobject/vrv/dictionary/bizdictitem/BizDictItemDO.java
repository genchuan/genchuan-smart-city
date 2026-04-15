package cn.iocoder.yudao.module.kitchen.dal.dataobject.vrv.dictionary.bizdictitem;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 业务字典项 DO
 *
 * @author 亘川智城
 */
@TableName("biz_dict_item")
@KeySequence("biz_dict_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizDictItemDO extends BaseDO {

    /**
     * [主键ID] 字典项唯一标识
     */
    @TableId
    private Long id;
    /**
     * [关联类型编码] 关联park_dict_type.uni_code
     */
    private String typeCode;
    /**
     * [字典键] 如：1、0、success
     */
    private String dictKey;
    /**
     * [字典显示名] 如：男、女、成功
     */
    private String dictLabel;
    /**
     * [颜色] 如：#1890ff
     */
    private String color;
    /**
     * [同类型内排序]
     */
    private Integer sort;
    /**
     * [字典项描述]
     */
    private String description;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [状态]如:0-禁用/1-启用
     */
    private Integer status;
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

package cn.iocoder.yudao.module.waterdetection.dal.dataobject.gb5749standard;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 《生活饮用水卫生标准》GB 5749-2022标准 DO
 *
 * @author 朱聪权
 */
@TableName("gc_gb5749_standard")
@KeySequence("gc_gb5749_standard_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gb5749StandardDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 指标名称
     */
    private String itemName;
    /**
     * 标准值
     */
    private String limitValue;
    /**
     * 排序序号
     */
    private Integer itemOrder;

}